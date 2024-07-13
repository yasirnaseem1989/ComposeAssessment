package com.yasir.compose.assessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteProblem(
    @SerializedName("Asthma")
    val asthma: List<RemoteAsthma>? = null,
    @SerializedName("Diabetes")
    val diabetes: List<RemoteDiabete>? = null
)
