package dev.mantistv.home;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.Configuration;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.AudioManager;
import android.net.*;
import android.os.*;
import android.provider.Settings;
import android.text.*;
import android.util.LruCache;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import dev.mantistv.home.AppCatalog.AppItem;
import java.util.*;
import java.util.concurrent.*;
import org.json.*;

public class MainActivity extends Activity {
  private final Handler handler = new Handler();
  private final ExecutorService worker = Executors.newSingleThreadExecutor();
  private final ArrayList<AppItem> apps = new ArrayList<>();
  private final LruCache<String, Bitmap> icons = new LruCache<String, Bitmap>(2048) {
    protected int sizeOf(String k, Bitmap b) {
      return Math.max(1, b.getByteCount() / 1024);
    }
  };
  private android.content.SharedPreferences prefs;
  private LinearLayout body, root;
  private String page = "Home", profile = "0", focusToken = "nav:Home";
  private String homeFocusToken = "";
  private boolean resumed = false, reduced = false, destroyed = false, firstResume = true;
  private long lastBack = 0;
  private int accent = 0xff86e9c6;
  private final Runnable idle = () -> screensaver();
  @Override
  public void onCreate(Bundle state) {
    super.onCreate(state);
    prefs = getSharedPreferences("mantistv", MODE_PRIVATE);
    profile = StoreRules.profileId(prefs.getString("profile", "0"));
    reduced = prefs.getBoolean(key("reduced"), false);
    discover();
    show("Home");
  }
  @Override
  protected void onResume() {
    super.onResume();
    resumed = true;
    if (!firstResume) {
      discover();
      if (root != null)
        show(page);
    }
    firstResume = false;
    armIdle();
  }
  @Override
  protected void onPause() {
    resumed = false;
    handler.removeCallbacks(idle);
    super.onPause();
  }
  @Override
  protected void onDestroy() {
    destroyed = true;
    handler.removeCallbacksAndMessages(null);
    worker.shutdownNow();
    icons.evictAll();
    super.onDestroy();
  }
  @Override
  public void onUserInteraction() {
    super.onUserInteraction();
    armIdle();
  }
  private void armIdle() {
    handler.removeCallbacks(idle);
    if (resumed && !page.equals("Screensaver"))
      handler.postDelayed(idle, 300000);
  }
  private int dp(float n) {
    return (int) (n * getResources().getDisplayMetrics().density + .5f);
  }
  private String key(String name) {
    return "p" + profile + "." + name;
  }
  private ArrayList<String> list(String name) {
    ArrayList<String> out = new ArrayList<>();
    try {
      JSONArray a = new JSONArray(prefs.getString(key(name), "[]"));
      for (int i = 0; i < a.length(); i++) out.add(a.getString(i));
    } catch (JSONException ignored) {
    }
    return out;
  }
  private void save(String name, List<String> value) {
    prefs.edit().putString(key(name), new JSONArray(value).toString()).apply();
  }
  private void discover() {
    apps.clear();
    apps.addAll(AppCatalog.discover(this));
  }
  private GradientDrawable glass(int color) {
    GradientDrawable d = new GradientDrawable();
    d.setColor(color);
    d.setCornerRadius(dp(18));
    d.setStroke(dp(1), 0x33ffffff);
    return d;
  }
  private TextView text(String value, int size) {
    TextView t = new TextView(this);
    t.setText(value);
    t.setTextSize(size);
    t.setTextColor(Color.WHITE);
    t.setPadding(dp(8), dp(6), dp(8), dp(6));
    return t;
  }
  private void focus(View v) {
    v.setFocusable(true);
    v.setOnFocusChangeListener((w, on) -> {
      if (on && w.getTag() instanceof String)
        focusToken = (String) w.getTag();
      w.setElevation(on ? dp(10) : 0);
      if (reduced) {
        w.setScaleX(1);
        w.setScaleY(1);
      } else
        w.animate().scaleX(on ? 1.045f : 1).scaleY(on ? 1.045f : 1).setDuration(140).start();
      w.setBackground(glass(on ? 0x995b8587 : 0x443d4e60));
    });
  }
  private Button button(String label, Runnable action) {
    Button b = new Button(this);
    b.setText(label);
    b.setTextColor(Color.WHITE);
    b.setTextSize(14);
    b.setAllCaps(false);
    b.setPadding(dp(16), dp(6), dp(16), dp(6));
    b.setBackground(glass(0x443d4e60));
    focus(b);
    b.setOnClickListener(v -> action.run());
    return b;
  }
  private void show(String next) {
    String desired = page.equals(next) ? focusToken : "nav:" + next;
    page = next;
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    root = new LinearLayout(this);
    root.setOrientation(LinearLayout.VERTICAL);
    if (next.equals("Home")) {
      HomeScene scene = new HomeScene(this, reduced, prefs.getBoolean(key("warm"), false),
          prefs.getString("name" + profile, "Profile " + (Integer.parseInt(profile) + 1)),
          homeApps(), new HomeScene.Actions() {
            public void open(AppItem app) { launch(app); }
            public void page(String name) { show(name); }
            public void profile() { profiles(); }
            public void focused(String tag) { focusToken = tag; homeFocusToken = tag; }
            public void appActions(AppItem app) { MainActivity.this.appActions(app); }
            public void icon(String pkg, ImageView image) { loadHomeTile(pkg, image); }
            public void banner(String pkg, ImageView image) { loadHomeBanner(pkg, image); }
          });
      root.addView(scene, new LinearLayout.LayoutParams(-1, -1));
      setContentView(root);
      scene.restoreFocus(desired.startsWith("home:") ? desired : homeFocusToken);
      armIdle();
      return;
    }
    root.setPadding(dp(32), dp(16), dp(32), dp(12));
    boolean warm = prefs.getBoolean(key("warm"), false);
    root.setBackground(new GradientDrawable(GradientDrawable.Orientation.TL_BR,
        warm ? new int[] {0xff342539, 0xff131326} : new int[] {0xff103944, 0xff101426}));
    setContentView(root);
    LinearLayout header = new LinearLayout(this);
    header.setGravity(Gravity.CENTER_VERTICAL);
    TextView brand = text("MANTIS  /  "
            + prefs.getString("name" + profile, "Profile " + (Integer.parseInt(profile) + 1)),
        18);
    brand.setTextColor(accent);
    header.addView(brand, new LinearLayout.LayoutParams(0, dp(48), 1));
    TextClock clock = new TextClock(this);
    clock.setFormat12Hour("HH:mm");
    clock.setFormat24Hour("HH:mm");
    clock.setTextSize(17);
    clock.setTextColor(Color.WHITE);
    clock.setPadding(dp(8), dp(6), dp(8), dp(6));
    header.addView(clock);
    root.addView(header);
    HorizontalScrollView navScroll = new HorizontalScrollView(this);
    navScroll.setHorizontalScrollBarEnabled(false);
    LinearLayout nav = new LinearLayout(this);
    nav.setPadding(0, dp(4), 0, dp(8));
    for (String name :
        new String[] {"Home", "Library", "Search", "Recent", "Watchlist", "Controls", "Settings"}) {
      Button b = button(name, () -> show(name));
      b.setTag("nav:" + name);
      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, dp(44));
      lp.setMargins(dp(4), 0, dp(4), 0);
      nav.addView(b, lp);
    }
    navScroll.addView(nav);
    root.addView(navScroll);
    ScrollView scroll = new ScrollView(this);
    scroll.setClipToPadding(false);
    body = new LinearLayout(this);
    body.setOrientation(LinearLayout.VERTICAL);
    body.setPadding(dp(8), dp(8), dp(8), dp(18));
    scroll.addView(body);
    root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));
    switch (next) {
      case "Library":
        library("All apps");
        break;
      case "Recent":
        section("Recently opened with MantisTV", selected(list("recent")));
        body.addView(text("This list tracks launches from MantisTV. Other apps and their tasks are "
                          + "managed by Fire OS.",
            14));
        break;
      case "Search":
        search();
        break;
      case "Controls":
        controls();
        break;
      case "Settings":
        settings();
        break;
      case "Watchlist":
        watchlist();
        break;
      default:
        show("Home");
        return;
    }
    body.addView(text(
        "Menu: controls    Hold OK on an app: favorites and app info    Back: Home / exit", 12));
    View restored = root.findViewWithTag(desired);
    if (restored != null)
      restored.requestFocus();
    else
      nav.getChildAt(0).requestFocus();
    armIdle();
  }
  private ArrayList<AppItem> homeApps() {
    ArrayList<String> installed = new ArrayList<>();
    for (AppItem app : apps) installed.add(app.pkg);
    return selected(StoreRules.homeOrder(installed, list("favorites"),
        Arrays.asList("com.netflix.ninja", "com.amazon.avod.thirdpartyclient",
            "com.amazon.firetv.youtube", "com.disney.disneyplus",
            "com.amazon.bueller.music", "com.amazon.venezia"),
        prefs.contains(key("favorites"))));
  }
  private void loadHomeBanner(String pkg, ImageView target) {
    target.setTag(pkg);
    target.setImageDrawable(null);
    if (worker.isShutdown()) return;
    worker.execute(() -> {
      if (!target.isAttachedToWindow() || !pkg.equals(target.getTag())) return;
      try {
        Drawable banner = getPackageManager().getApplicationBanner(pkg);
        if (banner == null) return;
        Bitmap bitmap = Bitmap.createBitmap(768, 432, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        banner.setBounds(0, 0, 768, 432);
        banner.draw(canvas);
        handler.post(() -> {
          if (!destroyed && target.isAttachedToWindow() && pkg.equals(target.getTag()))
            target.setImageBitmap(bitmap);
        });
      } catch (PackageManager.NameNotFoundException | RuntimeException ignored) {
      }
    });
  }
  private void loadHomeTile(String pkg, ImageView target) {
    String cacheKey = "home:" + pkg;
    Bitmap cached = icons.get(cacheKey);
    if (cached != null) { target.setImageBitmap(cached); return; }
    if (worker.isShutdown()) return;
    worker.execute(() -> {
      if (!target.isAttachedToWindow()) return;
      try {
        Drawable image = getPackageManager().getApplicationBanner(pkg);
        boolean banner = image != null;
        if (!banner) image = getPackageManager().getApplicationIcon(pkg);
        Bitmap bitmap = Bitmap.createBitmap(256, 144, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        image.setBounds(banner ? 0 : 68, banner ? 0 : 12, banner ? 256 : 188,
            banner ? 144 : 132);
        image.draw(canvas);
        icons.put(cacheKey, bitmap);
        handler.post(() -> {
          if (!destroyed && target.isAttachedToWindow()) target.setImageBitmap(bitmap);
        });
      } catch (PackageManager.NameNotFoundException | RuntimeException ignored) {
      }
    });
  }
  private ArrayList<AppItem> selected(List<String> names) {
    ArrayList<AppItem> out = new ArrayList<>();
    for (String p : names)
      for (AppItem a : apps)
        if (a.pkg.equals(p)) {
          out.add(a);
          break;
        }
    return out;
  }
  private void section(String title, List<AppItem> entries) {
    body.addView(text(title, 22));
    if (entries.isEmpty()) {
      body.addView(text("No applications here yet.", 16));
      return;
    }
    GridLayout grid = new GridLayout(this);
    grid.setColumnCount(6);
    grid.setClipChildren(false);
    grid.setPadding(dp(4), dp(8), dp(4), dp(10));
    for (AppItem a : entries) {
      LinearLayout card = new LinearLayout(this);
      card.setGravity(Gravity.CENTER);
      card.setOrientation(LinearLayout.VERTICAL);
      card.setPadding(dp(8), dp(8), dp(8), dp(6));
      card.setBackground(glass(0x443d4e60));
      card.setContentDescription(a.label);
      card.setTag("app:" + a.pkg);
      ImageView image = new ImageView(this);
      image.setScaleType(ImageView.ScaleType.FIT_CENTER);
      card.addView(image, new LinearLayout.LayoutParams(dp(46), dp(46)));
      loadIcon(a.pkg, image);
      TextView label = text(a.label, 13);
      label.setSingleLine(true);
      label.setEllipsize(TextUtils.TruncateAt.END);
      label.setGravity(Gravity.CENTER);
      card.addView(label, new LinearLayout.LayoutParams(-1, dp(34)));
      focus(card);
      card.setOnClickListener(v -> launch(a));
      card.setOnLongClickListener(v -> {
        appActions(a);
        return true;
      });
      GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
      lp.width = 0;
      lp.height = dp(104);
      lp.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
      lp.setMargins(dp(5), dp(5), dp(5), dp(5));
      grid.addView(card, lp);
    }
    body.addView(grid, new LinearLayout.LayoutParams(-1, -2));
  }
  private void loadIcon(String pkg, ImageView target) {
    Bitmap cached = icons.get(pkg);
    if (cached != null) {
      target.setImageBitmap(cached);
      return;
    }
    if (worker.isShutdown())
      return;
    worker.execute(() -> {
      try {
        Drawable icon = getPackageManager().getApplicationIcon(pkg);
        Bitmap b = Bitmap.createBitmap(96, 96, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        icon.setBounds(0, 0, 96, 96);
        icon.draw(c);
        icons.put(pkg, b);
        handler.post(() -> {
          if (!destroyed && target.isAttachedToWindow())
            target.setImageBitmap(b);
        });
      } catch (PackageManager.NameNotFoundException | RuntimeException ignored) {
      }
    });
  }
  private void launch(AppItem a) {
    try {
      startActivity(a.intent);
      save("recent", StoreRules.recent(list("recent"), a.pkg, 12));
    } catch (ActivityNotFoundException | SecurityException e) {
      Toast
          .makeText(this, "This app is unavailable. Refresh Library or open Fire OS settings.",
              Toast.LENGTH_LONG)
          .show();
    }
  }
  private void appActions(AppItem a) {
    boolean fav = list("favorites").contains(a.pkg);
    new AlertDialog.Builder(this)
        .setTitle(a.label)
        .setItems(new String[] {fav ? "Remove from favorites" : "Add to favorites",
                      "Application information"},
            (d, n) -> {
              if (n == 0) {
                ArrayList<String> items = list("favorites");
                if (fav)
                  items.remove(a.pkg);
                else if (items.size() < 24)
                  items.add(a.pkg);
                save("favorites", items);
                show(page);
              } else
                safe(new Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + a.pkg)));
            })
        .show();
  }
  private void library(String filter) {
    body.removeAllViews();
    LinearLayout categories = new LinearLayout(this);
    for (String category : new String[] {"All apps", "Favorites", "Recently opened"}) {
      Button b = button(category, () -> library(category));
      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, dp(48), 1);
      lp.setMargins(dp(4), 0, dp(4), dp(10));
      categories.addView(b, lp);
    }
    body.addView(categories);
    section(filter,
        filter.equals("Favorites")             ? selected(list("favorites"))
            : filter.equals("Recently opened") ? selected(list("recent"))
                                               : apps);
  }
  private void search() {
    body.addView(text("Search installed applications", 24));
    EditText input = new EditText(this);
    input.setSingleLine(true);
    input.setTextColor(Color.WHITE);
    input.setHintTextColor(0xffbbccdd);
    input.setHint("Name or package");
    body.addView(input);
    LinearLayout results = new LinearLayout(this);
    results.setOrientation(LinearLayout.VERTICAL);
    body.addView(results);
    input.addTextChangedListener(new TextWatcher() {
      public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
      public void onTextChanged(CharSequence s, int st, int before, int count) {
        results.removeAllViews();
        int matches = 0;
        for (AppItem app : apps)
          if (StoreRules.matches(app.label, app.pkg, s.toString())) {
            results.addView(button(app.label, () -> {
              ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                  .hideSoftInputFromWindow(input.getWindowToken(), 0);
              launch(app);
            }));
            matches++;
          }
        if (matches == 0)
          results.addView(text("No matching applications", 16));
      }
      public void afterTextChanged(Editable e) {}
    });
    input.setText("");
  }
  private void controls() {
    body.addView(text("Control Center", 26));
    ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    NetworkInfo net = cm.getActiveNetworkInfo();
    body.addView(text(net != null && net.isConnected() ? "Network · " + net.getTypeName()
                                                       : "Network · disconnected",
        17));
    android.util.DisplayMetrics m = new android.util.DisplayMetrics();
    getWindowManager().getDefaultDisplay().getRealMetrics(m);
    body.addView(text("Display · " + m.widthPixels + " × " + m.heightPixels + " · "
            + getWindowManager().getDefaultDisplay().getRefreshRate() + " Hz",
        17));
    AudioManager audio = (AudioManager) getSystemService(AUDIO_SERVICE);
    body.addView(button("Volume down", () -> volume(audio, -1)));
    body.addView(button("Volume up", () -> volume(audio, 1)));
    body.addView(text("HDMI and TV volume may require your TV remote.", 14));
    body.addView(button("Network settings", () -> safe(new Intent(Settings.ACTION_WIFI_SETTINGS))));
    body.addView(button("Sound settings", () -> safe(new Intent(Settings.ACTION_SOUND_SETTINGS))));
    body.addView(
        button("Display settings", () -> safe(new Intent(Settings.ACTION_DISPLAY_SETTINGS))));
    body.addView(button("Screensaver", () -> screensaver()));
    body.addView(text("System sleep remains controlled by the Fire TV remote. No privileged power "
                      + "commands are used.",
        14));
  }
  private void volume(AudioManager audio, int direction) {
    try {
      if (audio.isVolumeFixed())
        Toast.makeText(this, "Volume is controlled by your TV or receiver", Toast.LENGTH_SHORT)
            .show();
      else
        audio.adjustStreamVolume(AudioManager.STREAM_MUSIC, direction, AudioManager.FLAG_SHOW_UI);
    } catch (SecurityException e) {
      Toast.makeText(this, "Volume control unavailable", Toast.LENGTH_SHORT).show();
    }
  }
  private void safe(Intent intent) {
    try {
      if (intent.resolveActivity(getPackageManager()) != null)
        startActivity(intent);
      else
        Toast.makeText(this, "This shortcut is unavailable on this firmware", Toast.LENGTH_SHORT)
            .show();
    } catch (ActivityNotFoundException | SecurityException e) {
      Toast.makeText(this, "Fire OS does not allow this shortcut", Toast.LENGTH_SHORT).show();
    }
  }
  private void settings() {
    body.addView(text("Settings", 26));
    body.addView(button("Switch local profile", () -> profiles()));
    body.addView(button("Rename current profile", () -> {
      EditText field = new EditText(this);
      field.setSingleLine(true);
      field.setText(
          prefs.getString("name" + profile, "Profile " + (Integer.parseInt(profile) + 1)));
      new AlertDialog.Builder(this)
          .setTitle("Profile name")
          .setView(field)
          .setPositiveButton("Save",
              (d, w) -> {
                String name = field.getText().toString().trim();
                if (!name.isEmpty())
                  prefs.edit()
                      .putString("name" + profile, name.substring(0, Math.min(32, name.length())))
                      .apply();
                show("Settings");
              })
          .setNegativeButton("Cancel", null)
          .show();
    }));
    body.addView(
        button("Appearance · " + (prefs.getBoolean(key("warm"), false) ? "Dusk" : "Ocean"), () -> {
          prefs.edit().putBoolean(key("warm"), !prefs.getBoolean(key("warm"), false)).apply();
          show("Settings");
        }));
    body.addView(button("Reduce motion · " + (reduced ? "on" : "off"), () -> {
      reduced = !reduced;
      prefs.edit().putBoolean(key("reduced"), reduced).apply();
      show("Settings");
    }));
    body.addView(button("Refresh applications", () -> {
      icons.evictAll();
      discover();
      show("Library");
    }));
    body.addView(button(
        "System accessibility", () -> safe(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))));
    body.addView(button("System settings", () -> safe(new Intent(Settings.ACTION_SETTINGS))));
    body.addView(button("Return to Fire OS", () -> finish()));
    body.addView(text("Device · " + Build.MODEL + " / " + Build.DEVICE + "\nAndroid API "
            + Build.VERSION.SDK_INT + " · " + Arrays.toString(Build.SUPPORTED_ABIS)
            + "\nFirmware · " + Build.DISPLAY
            + ("\nMantisTV 0.5.0 preview · dev.mantistv.home\nOriginal native implementation. Not "
               + "affiliated with Apple or Amazon.\nFavorites, recent launches and watchlist are "
               + "local to each profile. Profile switching is not parental access control."),
        15));
  }
  private void profiles() {
    String[] names = new String[4];
    for (int i = 0; i < 4; i++) names[i] = prefs.getString("name" + i, "Profile " + (i + 1));
    new AlertDialog.Builder(this)
        .setTitle("Local profiles")
        .setSingleChoiceItems(names, Integer.parseInt(profile),
            (d, which) -> {
              profile = "" + which;
              reduced = prefs.getBoolean(key("reduced"), false);
              prefs.edit().putString("profile", profile).apply();
              d.dismiss();
              show("Home");
            })
        .setNegativeButton("Cancel", null)
        .show();
  }
  private void watchlist() {
    body.addView(text("Local watchlist", 25));
    body.addView(text("Save titles to remember. Streaming accounts and viewing progress are "
                      + "managed by each streaming app.",
        15));
    body.addView(button("Add a title", () -> {
      EditText field = new EditText(this);
      field.setSingleLine(true);
      new AlertDialog.Builder(this)
          .setTitle("Add title")
          .setView(field)
          .setPositiveButton("Save",
              (d, w) -> {
                String title = field.getText().toString().trim();
                if (!title.isEmpty()) {
                  ArrayList<String> items = list("watchlist");
                  if (items.size() < 100)
                    items.add(title.substring(0, Math.min(200, title.length())));
                  save("watchlist", items);
                  show("Watchlist");
                }
              })
          .setNegativeButton("Cancel", null)
          .show();
    }));
    for (String title : list("watchlist")) {
      Button b = button(title,
          ()
              -> new AlertDialog.Builder(this)
                     .setTitle(title)
                     .setMessage("Remove this title from your local watchlist?")
                     .setPositiveButton("Remove",
                         (d, w) -> {
                           ArrayList<String> items = list("watchlist");
                           items.remove(title);
                           save("watchlist", items);
                           show("Watchlist");
                         })
                     .setNegativeButton("Keep", null)
                     .show());
      body.addView(b);
    }
  }
  private void screensaver() {
    if (!resumed)
      return;
    handler.removeCallbacks(idle);
    page = "Screensaver";
    LandscapeView scene = new LandscapeView(this, true, reduced);
    scene.setFocusable(true);
    scene.setContentDescription("MantisTV landscape screensaver. Press Back to return home.");
    setContentView(scene);
    scene.requestFocus();
  }
  @Override
  public boolean dispatchKeyEvent(KeyEvent e) {
    if (page.equals("Screensaver") && e.getAction() == KeyEvent.ACTION_DOWN) {
      show("Home");
      return true;
    }
    if (e.getKeyCode() == KeyEvent.KEYCODE_MENU) {
      if (e.getAction() == KeyEvent.ACTION_DOWN && e.getRepeatCount() == 0)
        show(page.equals("Controls") ? "Home" : "Controls");
      return true;
    }
    return super.dispatchKeyEvent(e);
  }
  @Override
  public void onBackPressed() {
    if (!page.equals("Home")) {
      show("Home");
      return;
    }
    long now = SystemClock.elapsedRealtime();
    if (now - lastBack < 2000) {
      finish();
      return;
    }
    lastBack = now;
    Toast.makeText(this, "Press Back again to return to Fire OS", Toast.LENGTH_SHORT).show();
  }
  @Override
  public void onTrimMemory(int level) {
    super.onTrimMemory(level);
    if (level >= TRIM_MEMORY_RUNNING_LOW)
      icons.evictAll();
  }
}
