package com.yasir.compose.assessment.data.remote

import com.yasir.compose.assessment.data.model.Medicine
import com.yasir.compose.assessment.domain.ResultWrapper
import com.yasir.compose.assessment.domain.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: MedicineApiService
) {

    suspend fun getMedicines(): Flow<ResultWrapper<List<Medicine>>> {
        return flow {
            emit(
                safeApiCall {
                    apiService.getMedicines()
                }
            )
        }
    }
}