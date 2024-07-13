package com.yasir.compose.assessment.ui.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppProgressBar(
    modifier: Modifier = Modifier,
    showProgress: Boolean = true
) {
    if (showProgress.not())
        return
    CircularProgressIndicator(
        modifier = modifier
    )
}