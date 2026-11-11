package com.leslie.tvhome

import org.junit.Assert.assertEquals
import org.junit.Test

class DockLayoutTest {
    @Test fun mantisDefaultDoesNotChangeUpstreamDefault() {
        assertEquals(6, DockLayout.defaultColumns(mantis = true))
        assertEquals(5, DockLayout.defaultColumns(mantis = false))
    }

    @Test fun nextRowRemainsDiscoverableForBothColumnChoicesAndScreenSizes() {
        for (height in listOf(720, 1080, 2160)) {
            val width = height * 16 / 9
            for (columns in listOf(5, 6)) {
                val tile = DockLayout.tileWidth(width, width / 20, width / 40, columns) * 9 / 16
                val rows = DockLayout.referenceRows(height, tile, height / 15)
                org.junit.Assert.assertTrue(rows.firstTop > 0)
                org.junit.Assert.assertTrue(rows.firstTop + tile < height)
                org.junit.Assert.assertTrue(rows.pitch - tile >= height / 15)
                val nextTop = rows.firstTop + rows.pitch
                org.junit.Assert.assertTrue(nextTop < height)
                org.junit.Assert.assertTrue(nextTop >= height * 0.97f)
            }
        }
    }

    @Test fun largerLabelsGainRoomWithoutLosingTheNextRowHint() {
        val normal = DockLayout.referenceRows(1080, 139, 80)
        val large = DockLayout.referenceRows(1080, 139, 140)
        org.junit.Assert.assertTrue(large.pitch > normal.pitch)
        assertEquals(normal.firstTop + normal.pitch, large.firstTop + large.pitch)
        org.junit.Assert.assertTrue(large.pitch - 139 >= 140)
    }

    @Test fun existingUsersKeepTheOriginalFiveColumns() {
        assertEquals(5, DockLayout.DEFAULT_COLUMNS)
        assertEquals(5, DockLayout.normalized(0))
        assertEquals(5, DockLayout.normalized(-1))
        assertEquals(5, DockLayout.normalized(7))
        assertEquals(5, DockLayout.normalized(5))
    }

    @Test fun sixIsExplicitAndTogglingIsReversible() {
        assertEquals(6, DockLayout.normalized(6))
        assertEquals(6, DockLayout.next(5))
        assertEquals(5, DockLayout.next(6))
    }

    @Test fun bothLayoutsFitTheOriginalScreenWidth() {
        val screen = 1920
        val side = 112
        val gap = 56
        assertEquals(294, DockLayout.tileWidth(screen, side, gap, 5))
        assertEquals(236, DockLayout.tileWidth(screen, side, gap, 6))
        assertEquals(1, DockLayout.tileWidth(100, 80, 56, 6))
    }

    @Test fun dockBottomPositionRemainsConstant() {
        val baseTop = 804
        val heightFive = 165
        val heightSix = 132
        assertEquals(baseTop, DockLayout.dockTop(baseTop, heightFive, heightFive))
        assertEquals(baseTop + heightFive, DockLayout.dockTop(baseTop, heightFive, heightSix) + heightSix)
    }
}
