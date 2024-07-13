package com.yasir.compose.assessment.utils.ext

private const val ZERO = 0

fun Int?.orZero(): Int = this ?: ZERO

fun Long?.orZero(): Long = this ?: ZERO.toLong()
