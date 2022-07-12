package com.khalifa.foodapp.domain.util

import java.text.DecimalFormat

fun Double.formatDecimalNumberWithUnit(unit: String = "") = DecimalFormat("#.##$unit").format(this)

