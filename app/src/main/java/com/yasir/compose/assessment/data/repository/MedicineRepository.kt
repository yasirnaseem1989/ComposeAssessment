package com.yasir.compose.assessment.data.repository

import com.yasir.compose.assessment.data.model.Medicine
import com.yasir.compose.assessment.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {

    suspend fun getAllMedicines(): Flow<ResultWrapper<List<Medicine>>>

    suspend fun insertAll(medicines: List<Medicine>)
}