package com.yasir.compose.assessment.utils.ext

fun Boolean?.orFalse(): Boolean = this ?: false
fun Boolean?.orTrue(): Boolean = this ?: true
