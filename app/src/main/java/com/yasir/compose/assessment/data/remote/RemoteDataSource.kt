package com.yasir.compose.assessment.data.remote

import com.yasir.compose.assessment.domain.model.Problem

interface RemoteDataSource {

    suspend fun getMedicines(): List<Problem>
}