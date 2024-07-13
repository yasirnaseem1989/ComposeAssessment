package com.yasir.compose.assessment.data.local

import com.yasir.compose.assessment.domain.model.Medicine
import javax.inject.Inject

class LocalMedicineDataSource @Inject constructor(
    private val medicineDao: MedicineDao
): LocalDataSource {

    override suspend fun getAllMedicines(): List<Medicine> = medicineDao.getAllMedicines()

    override suspend fun insertAll(medicines: List<Medicine>) {
        medicineDao.insertAll(medicines)
    }

    override suspend fun getMedicineById(medicineId: Int): Medicine =
        medicineDao.getMedicineById(medicineId)
}