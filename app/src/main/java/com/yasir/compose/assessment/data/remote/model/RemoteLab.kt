package com.yasir.compose.assessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteLab(
    @SerializedName("missing_field")
    val missingField: String? = null
)
