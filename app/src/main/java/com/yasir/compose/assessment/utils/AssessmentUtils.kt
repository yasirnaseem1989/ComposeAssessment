package com.yasir.compose.assessment.utils

import java.util.Calendar

object AssessmentUtils {

    fun getGreetingMessage(): String {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }
}