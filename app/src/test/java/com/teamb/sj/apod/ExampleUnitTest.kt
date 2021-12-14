package com.teamb.sj.apod

import com.teamb.sj.apod.core.util.Utils
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `valid date format`() {
        assertFalse(Utils.isValidDateFormat(""))
        assertTrue(Utils.isValidDateFormat("1994-12-02"))
        assertFalse(Utils.isValidDateFormat("1994-13-02"))
        assertFalse(Utils.isValidDateFormat("22-02-1994"))
        assertFalse(Utils.isValidDateFormat("22-2-1994"))
    }

    @Test
    fun `test get local date from string`() {
        val a = Utils.getLocalDateFromString("2021-12-12")
        assertEquals(a.dayOfMonth, 12)
        assertEquals(a.monthValue, 12)
        assertEquals(a.year, 2021)
    }
}