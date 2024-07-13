package com.yasir.compose.assessment.domain.model

data class ClassName(
    val associatedDrug: List<AssociatedDrug> = emptyList(),
    val associatedDrug2: List<AssociatedDrug2> = emptyList()
)
