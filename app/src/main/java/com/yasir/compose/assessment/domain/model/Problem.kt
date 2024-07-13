package com.yasir.compose.assessment.domain.model

data class Problem(
    val asthma: List<Asthma> = emptyList(),
    val diabetes: List<Diabete> = emptyList()
)
