package com.leslie.tvhome

/** Restore an app's identity across grid rebuilds, never its old row/column coordinates. */
internal object HomeFocusState {
    fun restoreIndex(packages: List<String>, packageName: String?, fallbackPosition: Int): Int {
        if (packages.isEmpty()) return -1
        if (packageName != null) return packages.indexOf(packageName).coerceAtLeast(0)
        return fallbackPosition.coerceIn(0, packages.lastIndex)
    }
}
