package com.yasir.compose.assessment.domain

import com.yasir.compose.assessment.data.model.Medicine
import com.yasir.compose.assessment.data.repository.MedicineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(
    private val repository: MedicineRepository
) {

    suspend fun invoke(): Flow<ResultWrapper<List<Medicine>>> {
        return repository.getAllMedicines()
            .flowOn(Dispatchers.IO)
    }
}