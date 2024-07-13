package com.yasir.compose.assessment.data.remote

import com.yasir.compose.assessment.data.mapper.MedicineMapper
import com.yasir.compose.assessment.domain.model.Problem
import javax.inject.Inject

class RemoteMedicineDataSource @Inject constructor(
    private val apiService: MedicineApiService,
    private val medicineMapper: MedicineMapper,
) : RemoteDataSource {

    override suspend fun getMedicines(): List<Problem> {
        val remoteMedicine = apiService.getMedicines().problems.orEmpty()
        return if (remoteMedicine.isEmpty()) {
            emptyList()
        } else {
            medicineMapper.mapToProblems(remoteMedicine)
        }
    }

}