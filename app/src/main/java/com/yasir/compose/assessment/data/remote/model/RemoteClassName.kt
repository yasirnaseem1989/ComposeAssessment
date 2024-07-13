package com.yasir.compose.assessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteClassName(
    @SerializedName("associatedDrug")
    val associatedDrug: List<RemoteAssociatedDrug>? = null,
    @SerializedName("associatedDrug2")
    val associatedDrug2: List<RemoteAssociatedDrug2>? = null
)
