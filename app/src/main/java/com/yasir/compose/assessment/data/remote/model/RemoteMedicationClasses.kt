package com.yasir.compose.assessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteMedicationClasses(
    @SerializedName("className")
    val className: List<RemoteClassName>? = null,
    @SerializedName("className2")
    val className2: List<RemoteClassName2>? = null
)
