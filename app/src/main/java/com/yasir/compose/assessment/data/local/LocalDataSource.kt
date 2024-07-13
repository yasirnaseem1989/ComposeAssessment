package com.yasir.compose.assessment.data.local

import com.yasir.compose.assessment.domain.model.Medicine

interface LocalDataSource {

    suspend fun getAllMedicines(): List<Medicine>

    suspend fun insertAll(medicines: List<Medicine>)

    suspend fun getMedicineById(medicineId: Int): Medicine
}