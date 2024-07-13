package com.yasir.compose.assessment.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteMedicine(
    @SerializedName("problems")
    val problems: List<RemoteProblem>? = null
)
