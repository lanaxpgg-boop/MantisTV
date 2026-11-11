package com.leslie.tvhome

import org.junit.Assert.assertEquals
import org.junit.Test

class HomeFocusStateTest {
    @Test fun reorderingKeepsTheSameAppInsteadOfItsOldPosition() {
        assertEquals(2, HomeFocusState.restoreIndex(listOf("c", "a", "b"), "b", 1))
        assertEquals(0, HomeFocusState.restoreIndex(listOf("b", "c", "a"), "b", 1))
    }

    @Test fun removedAppFallsBackToHomeAndEmptyCatalogHasNoTarget() {
        assertEquals(0, HomeFocusState.restoreIndex(listOf("a", "c"), "b", 1))
        assertEquals(-1, HomeFocusState.restoreIndex(emptyList(), "b", 1))
    }

    @Test fun legacyPositionIsBoundedWhenNoIdentityWasSaved() {
        assertEquals(1, HomeFocusState.restoreIndex(listOf("a", "b"), null, 99))
        assertEquals(0, HomeFocusState.restoreIndex(listOf("a", "b"), null, -1))
    }
}
