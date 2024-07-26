package com.yasir.compose.assessment.data.remote

import com.yasir.compose.assessment.data.mapper.MedicineMapper
import com.yasir.compose.assessment.data.repository.Result
import com.yasir.compose.assessment.domain.model.Problem
import javax.inject.Inject

class RemoteMedicineDataSource @Inject constructor(
    private val apiService: MedicineApiService,
    private val medicineMapper: MedicineMapper,
    private val networkHandler: NetworkHandler,
) : RemoteDataSource {

    override suspend fun getMedicines(): Result<List<Problem>> {
        return networkHandler.safeApiCall {
            val remoteMedicine = apiService.getMedicines().problems.orEmpty()
            if (remoteMedicine.isEmpty()) {
                emptyList()
            } else {
                medicineMapper.mapToProblems(remoteMedicine)
            }
        }
    }

}