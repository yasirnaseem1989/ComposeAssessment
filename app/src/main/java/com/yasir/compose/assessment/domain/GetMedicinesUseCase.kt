package com.yasir.compose.assessment.domain

import com.yasir.compose.assessment.data.repository.MedicineRepository
import com.yasir.compose.assessment.data.repository.Result
import com.yasir.compose.assessment.domain.model.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(
    private val repository: MedicineRepository,
) {
    suspend fun invoke(): Result<List<Medicine>>     =
        withContext(Dispatchers.IO) {
            repository.getAllMedicines()
        }
}

