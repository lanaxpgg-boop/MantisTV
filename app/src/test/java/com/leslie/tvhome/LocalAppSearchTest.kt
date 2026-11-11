package com.leslie.tvhome

import org.junit.Assert.*
import org.junit.Test

class LocalAppSearchTest {
    @Test fun punctuationAndWhitespaceNeverMatchTheWholeCatalog() {
        for (q in listOf("", "  ", "---", "!?", "🙂")) assertEquals(-1, LocalAppSearch.score("Appstore", "com.store", q))
    }
    @Test fun matchingKeepsPrefixWordSubstringAndPackagePriorities() {
        assertEquals(0, LocalAppSearch.score("Prime Video", "com.video", "prime"))
        assertEquals(1, LocalAppSearch.score("Prime Video", "com.video", "video"))
        assertEquals(2, LocalAppSearch.score("Prime Video", "com.video", "ime"))
        assertEquals(3, LocalAppSearch.score("Prime Video", "com.video", "com.vi"))
        assertEquals(-1, LocalAppSearch.score("Prime Video", "com.video", "unknown"))
    }
    @Test fun normalizationHandlesCaseSpacingAndUnicodeLetters() {
        assertEquals(0, LocalAppSearch.score("Sony LIV", "", "sonyliv"))
        assertEquals(0, LocalAppSearch.score("Čuvar", "", " ČU "))
        assertEquals(0, LocalAppSearch.score("电视", "", "电"))
    }
    @Test fun exactLabelPrefixesRankAheadOfPackageOnlyMatches() {
        val entries = listOf("com.app" to "Zebra", "other" to "Com App")
        assertEquals(listOf("other", "com.app"), LocalAppSearch.ranked(entries, "com", { it.first }, { it.second }).map { it.first })
    }
    @Test fun resultsAreBoundedDeduplicatedAndDeterministic() {
        val entries = (0..30).reversed().map { "pkg.%02d".format(it) to "App %02d".format(it) }
        val result = LocalAppSearch.ranked(entries + entries.first(), "app", { it.first }, { it.second })
        assertEquals(12, result.size)
        assertEquals(12, result.map { it.first }.toSet().size)
        assertEquals("pkg.00", result.first().first)
        assertTrue(LocalAppSearch.ranked(entries, "missing", { it.first }, { it.second }).isEmpty())
    }
}
