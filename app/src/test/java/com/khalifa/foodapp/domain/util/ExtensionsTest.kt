package com.khalifa.foodapp.domain.util

import com.khalifa.foodapp.presentation.constant.Constants.GRAM
import org.junit.Assert
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `test formatDecimalNumberWithUnit with three decimal numbers & unit returns two decimal number & unit`() {
        val expected = "8.29g"
        val actual = 8.293.formatDecimalNumberWithUnit(GRAM)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test formatDecimalNumberWithUnit with zero decimal number & no unit returns ّInteger number`() {
        val expected = "174"
        val actual = 174.0.formatDecimalNumberWithUnit("")
        Assert.assertEquals(expected, actual)
    }
}
