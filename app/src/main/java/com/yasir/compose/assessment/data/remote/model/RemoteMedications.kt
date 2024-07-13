package com.yasir.compose.assessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteMedications(
    @SerializedName("medicationsClasses")
    val medicationClasses: List<RemoteMedicationClasses> = listOf()
)
