package com.leslie.tvhome

import android.app.Activity
import android.app.ActivityOptions
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

/** Foreground-only, installed-app Search. No network catalogue, microphone or system changes. */
class AppSearchActivity : Activity(), TileAdapter.Listener {
    private lateinit var repo: AppRepo
    private lateinit var adapter: TileAdapter
    private lateinit var input: EditText
    private lateinit var status: android.widget.TextView
    private lateinit var grid: RecyclerView
    private lateinit var close: PillButton
    private val main = Handler(Looper.getMainLooper())
    private val catalogWorker = Executors.newSingleThreadExecutor()
    private var catalog: List<AppEntry> = emptyList()
    private var catalogGeneration = 0L
    private var loaded = false
    private var active = false
    private var columns = 6
    private val queryTask = Runnable { if (active) updateResults() }

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        if (!MantisExperiment.enabled) { finish(); return }
        Tween.readScale(this)
        repo = AppRepo(this)
        columns = Prefs(this).dockColumns
        val body = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(48), dp(28), dp(48), dp(24))
            setBackgroundColor(Color.rgb(12, 19, 28))
        }
        body.addView(text(28f, bold = true).apply { text = getString(R.string.app_search_title) })
        body.addView(text(14f, Color.LTGRAY).apply {
            text = getString(R.string.app_search_scope)
        }, LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = dp(16) })
        input = EditText(this).apply {
            id = View.generateViewId()
            setSingleLine()
            textSize = 20f * Prefs(this@AppSearchActivity).textScale
            setTextColor(Color.WHITE)
            setHintTextColor(Color.LTGRAY)
            hint = getString(R.string.app_search_hint)
            background = rounded(Color.argb(70, 255, 255, 255), dp(14).toFloat())
            setPadding(dp(16), dp(12), dp(16), dp(12))
            filters = arrayOf(android.text.InputFilter.LengthFilter(LocalAppSearch.MAX_QUERY))
            imeOptions = EditorInfo.IME_ACTION_SEARCH or EditorInfo.IME_FLAG_NO_EXTRACT_UI
            inputType = android.text.InputType.TYPE_CLASS_TEXT
            setText(state?.getString("query").orEmpty().take(LocalAppSearch.MAX_QUERY))
            setSelection(text.length)
        }
        body.addView(input, LinearLayout.LayoutParams(-1, -2))
        status = text(14f, Color.LTGRAY).apply {
            text = getString(R.string.app_search_loading)
            setPadding(0, dp(12), 0, dp(8))
        }
        body.addView(status, LinearLayout.LayoutParams(-1, -2))
        val side = dp(48)
        val gap = dp(24)
        val tileW = DockLayout.tileWidth(resources.displayMetrics.widthPixels, side + dp(12), gap, columns)
        val tileH = tileW * 9 / 16
        adapter = TileAdapter(repo, tileW, tileH, this).apply {
            setHasStableIds(true)
            allowLongPress = false
            tvosFocus = Prefs(this@AppSearchActivity).focusTvos(this@AppSearchActivity)
        }
        grid = RecyclerView(this).apply {
            layoutManager = GridLayoutManager(this@AppSearchActivity, columns)
            adapter = this@AppSearchActivity.adapter
            itemAnimator = null
            clipChildren = false
            clipToPadding = false
            setPadding(dp(12), dp(12), dp(12), dp(52))
            overScrollMode = View.OVER_SCROLL_NEVER
            setItemViewCacheSize(LocalAppSearch.MAX_RESULTS)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(out: android.graphics.Rect, view: View,
                    parent: RecyclerView, state: RecyclerView.State) {
                    val column = parent.getChildAdapterPosition(view).coerceAtLeast(0) % columns
                    out.left = column * gap / columns
                    out.right = gap - (column + 1) * gap / columns
                    out.bottom = dp(52 * Prefs(this@AppSearchActivity).textScale.coerceAtLeast(1f))
                }
            })
        }
        body.addView(grid, LinearLayout.LayoutParams(-1, 0, 1f))
        close = PillButton(this, getString(android.R.string.cancel)) { finish() }.apply {
            id = View.generateViewId()
        }
        body.addView(close, LinearLayout.LayoutParams(dp(240), dp(48)).apply { gravity = Gravity.END })
        setContentView(body)
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                main.removeCallbacks(queryTask)
                main.postDelayed(queryTask, 200)
            }
            override fun afterTextChanged(s: Editable?) = Unit
        })
        input.setOnEditorActionListener { _, action, _ ->
            if (action != EditorInfo.IME_ACTION_SEARCH) false else {
                main.removeCallbacks(queryTask)
                updateResults()
                getSystemService(InputMethodManager::class.java)?.hideSoftInputFromWindow(input.windowToken, 0)
                if (adapter.itemCount > 0) focusResult(0)
                true
            }
        }
        input.setOnKeyListener { _, code, event ->
            if (code != KeyEvent.KEYCODE_DPAD_DOWN || event.action != KeyEvent.ACTION_DOWN) false else {
                main.removeCallbacks(queryTask)
                updateResults()
                if (adapter.itemCount == 0) close.requestFocus() else focusResult(0)
                true
            }
        }
        input.requestFocus()
    }

    override fun onResume() {
        super.onResume()
        if (!::repo.isInitialized) return
        active = true
        loaded = false
        adapter.submit(emptyList())
        status.text = getString(R.string.app_search_loading)
        val generation = ++catalogGeneration
        catalogWorker.execute {
            val result = runCatching {
                val hidden = Prefs(this).hidden.toMutableSet()
                Folders(this).read().filter { it.id in hidden }.forEach { hidden.addAll(it.members) }
                repo.allApps().filter { it.input == null && it.folder == null &&
                    !repo.isOwn(it.pkg) && it.pkg !in hidden }
                    .map { if (it.label.isBlank()) it.copy(label = it.pkg) else it }
            }
            main.post {
                if (!active || isDestroyed || generation != catalogGeneration) return@post
                if (result.isFailure) {
                    catalog = emptyList()
                    adapter.submit(emptyList())
                    status.text = getString(R.string.app_search_unavailable)
                } else {
                    catalog = result.getOrThrow()
                    loaded = true
                    updateResults()
                }
            }
        }
    }

    private fun updateResults() {
        if (!loaded || !active) return
        val query = input.text.toString()
        val results = LocalAppSearch.ranked(catalog, query, { it.pkg }, { it.label })
        adapter.submit(results)
        status.text = when {
            query.isBlank() -> getString(R.string.app_search_scope)
            results.isEmpty() -> getString(R.string.app_search_empty)
            else -> resources.getQuantityString(R.plurals.app_search_count, results.size,
                results.size, LocalAppSearch.MAX_RESULTS)
        }
    }

    private fun focusResult(position: Int) {
        grid.scrollToPosition(position)
        grid.post {
            if (active && !isDestroyed) grid.findViewHolderForAdapterPosition(position)?.itemView?.requestFocus()
        }
    }

    override fun onTileFocused(pos: Int, cell: TileCell) = Unit
    override fun onTileLongPressed(pos: Int, cell: TileCell) = Unit
    override fun onTileClicked(pos: Int, cell: TileCell) {
        if (!active || isFinishing) return
        val app = adapter.apps.getOrNull(pos) ?: return
        try {
            val intent = repo.launchIntent(app) ?: error("No launch target")
            val options = ActivityOptions.makeScaleUpAnimation(cell.card, 0, 0, cell.card.width, cell.card.height)
            startActivity(intent, options.toBundle())
            RecentApps.record(this, app.pkg)
            HomeReturnHandoff.set(app.pkg)
            finish()
        } catch (_: Exception) {
            Toast.makeText(this, getString(R.string.i18n_homeactivity_005, app.label), Toast.LENGTH_SHORT).show()
        }
    }
    override fun onTileKey(pos: Int, cell: TileCell, keyCode: Int): Boolean {
        adapter.lastDx = when (keyCode) { KeyEvent.KEYCODE_DPAD_LEFT -> -1; KeyEvent.KEYCODE_DPAD_RIGHT -> 1; else -> 0 }
        adapter.lastDy = when (keyCode) { KeyEvent.KEYCODE_DPAD_UP -> -1; KeyEvent.KEYCODE_DPAD_DOWN -> 1; else -> 0 }
        return when {
            keyCode == KeyEvent.KEYCODE_DPAD_UP && pos < columns -> { input.requestFocus(); true }
            keyCode == KeyEvent.KEYCODE_DPAD_DOWN && pos + columns >= adapter.itemCount -> {
                val target = AppSearchNavigation.downTarget(pos, adapter.itemCount, columns)
                if (target < 0) close.requestFocus() else focusResult(target)
                true
            }
            else -> false
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        if (::input.isInitialized) outState.putString("query", input.text.toString())
        super.onSaveInstanceState(outState)
    }
    override fun onPause() {
        active = false
        ++catalogGeneration
        main.removeCallbacks(queryTask)
        super.onPause()
    }
    override fun onDestroy() {
        active = false
        ++catalogGeneration
        main.removeCallbacksAndMessages(null)
        if (::grid.isInitialized) grid.adapter = null
        if (::adapter.isInitialized) adapter.close()
        catalogWorker.shutdownNow()
        if (::repo.isInitialized) repo.invalidate()
        super.onDestroy()
    }
}
