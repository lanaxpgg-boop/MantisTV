package com.leslie.tvhome

/**
 * tvOS Home uses one consistent column count for its glass Dock and app grid.
 * Standard/guard retain five; Mantis starts at six unless a column choice was saved.
 * This is shared UI layout policy, never a vendor or installation adapter.
 */
object DockLayout {
    const val DEFAULT_COLUMNS = 5
    const val SIX_COLUMNS = 6

    fun defaultColumns(mantis: Boolean): Int = if (mantis) SIX_COLUMNS else DEFAULT_COLUMNS

    data class HomeRows(val firstTop: Int, val pitch: Int)

    /** Inherited tvOS26 composition: a small next-row hint, with room for real label metrics. */
    fun referenceRows(screenHeight: Int, tileHeight: Int, labelClearance: Int): HomeRows {
        require(screenHeight > 0 && tileHeight > 0 && labelClearance >= 0)
        val gap = maxOf((screenHeight * 0.09f).toInt(), labelClearance)
        val pitch = tileHeight + gap
        val peek = (screenHeight * 0.02f).toInt().coerceAtLeast(1)
        return HomeRows((screenHeight - peek - pitch).coerceAtLeast(0), pitch)
    }

    fun normalized(columns: Int): Int = if (columns == SIX_COLUMNS) SIX_COLUMNS else DEFAULT_COLUMNS

    fun next(columns: Int): Int =
        if (normalized(columns) == DEFAULT_COLUMNS) SIX_COLUMNS else DEFAULT_COLUMNS

    fun tileWidth(screenWidth: Int, side: Int, gap: Int, columns: Int): Int {
        val count = normalized(columns)
        return ((screenWidth - side * 2 - gap * (count - 1)) / count).coerceAtLeast(1)
    }

    /** Keep the Dock's lower edge in the same position when switching to six smaller tiles. */
    fun dockTop(baseTop: Int, defaultTileHeight: Int, selectedTileHeight: Int): Int =
        baseTop + defaultTileHeight - selectedTileHeight
}
