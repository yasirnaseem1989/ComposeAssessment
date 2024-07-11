package com.yasir.compose.assessment.data.local

import com.yasir.compose.assessment.data.model.Medicine
import com.yasir.compose.assessment.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val medicineDao: MedicineDao
) {

    fun getAllMedicines(): Flow<ResultWrapper<List<Medicine>>> {
        return medicineDao.getAllMedicines()
    }

    fun insertAll(medicines: List<Medicine>) {
        medicineDao.insertAll(medicines)
    }
}