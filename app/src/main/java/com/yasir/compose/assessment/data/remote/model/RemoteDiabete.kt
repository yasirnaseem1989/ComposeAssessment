package com.yasir.compose.assessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteDiabete(
    @SerializedName("labs")
    val labs: List<RemoteLab>? = null,
    @SerializedName("medications")
    val medications: List<RemoteMedications>? = null
)
