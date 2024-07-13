package com.yasir.compose.assessment.domain.model

data class Diabete(
    val labs: List<Lab> = listOf(),
    val medications: List<Medication> = emptyList()
)
