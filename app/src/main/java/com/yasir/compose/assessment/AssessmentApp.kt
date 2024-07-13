package com.yasir.compose.assessment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssessmentApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}