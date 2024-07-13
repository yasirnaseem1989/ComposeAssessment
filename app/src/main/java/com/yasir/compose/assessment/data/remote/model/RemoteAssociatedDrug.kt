package com.yasir.compose.assessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteAssociatedDrug(
    @SerializedName("dose")
    val dose: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("strength")
    val strength: String? = null
)
