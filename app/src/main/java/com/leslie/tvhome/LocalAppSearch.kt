package com.leslie.tvhome

import java.util.Locale

/**
 * Local matching adapted from Hume Launcher Search.java (Apache-2.0).
 * Copyright2026 Petrobot; revision19bd8b1fa2d535929c768e6e197a9f464e89f953.
 * Changes: empty-normalization guards, bounded unique results and stable tie breaking.
 * See assets/hume-NOTICE.txt and hume-Apache-2.0.txt. No provider/network code.
 */
internal object LocalAppSearch {
    const val MAX_QUERY = 64
    const val MAX_RESULTS = 12
    private val wordBreaks = Regex("[^\\p{L}\\p{N}]+")

    private fun norm(value: String) = value.lowercase(Locale.ROOT).filter { it.isLetterOrDigit() }

    fun score(name: String, keywords: String, query: String): Int {
        val q = query.trim().take(MAX_QUERY).lowercase(Locale.ROOT)
        val normalized = norm(q)
        if (normalized.isEmpty()) return -1
        val n = name.lowercase(Locale.ROOT)
        val normalizedName = norm(n)
        if (n.startsWith(q) || normalizedName.startsWith(normalized)) return 0
        if (n.split(wordBreaks).any { it.isNotEmpty() && it.startsWith(q) }) return 1
        if (normalized.length >= 2 && normalizedName.contains(normalized)) return 2
        if (keywords.lowercase(Locale.ROOT).split(' ').any { it.startsWith(q) }) return 3
        return -1
    }

    fun <T> ranked(items: List<T>, query: String, key: (T) -> String, label: (T) -> String): List<T> =
        items.asSequence().distinctBy(key)
            .map { it to score(label(it), key(it), query) }
            .filter { it.second >= 0 }
            .sortedWith(compareBy<Pair<T, Int>> { it.second }
                .thenBy { label(it.first).lowercase(Locale.ROOT) }.thenBy { key(it.first) })
            .take(MAX_RESULTS).map { it.first }.toList()
}

/** A short last row remains reachable from every column above it. */
internal object AppSearchNavigation {
    fun downTarget(position: Int, count: Int, columns: Int): Int {
        if (columns <= 0 || position !in 0 until count) return -1
        if (position / columns >= (count - 1) / columns) return -1
        return (position + columns).coerceAtMost(count - 1)
    }
}
