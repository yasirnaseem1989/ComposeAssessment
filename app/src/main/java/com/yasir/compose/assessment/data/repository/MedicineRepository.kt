package com.yasir.compose.assessment.data.repository

import com.yasir.compose.assessment.data.local.LocalDataSource
import com.yasir.compose.assessment.data.mapper.MedicineViewItemMapper
import com.yasir.compose.assessment.data.remote.RemoteDataSource
import com.yasir.compose.assessment.domain.model.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val medicineViewItemMapper: MedicineViewItemMapper,
) {
    suspend fun getAllMedicines(): List<Medicine> {

        val medicineData: List<Medicine> = localDataSource.getAllMedicines()

        return medicineData.ifEmpty {
            val data = remoteDataSource.getMedicines()
            if (data.isEmpty()) {
                medicineData
            } else {
                val medicines =
                    data.firstOrNull()?.diabetes?.firstOrNull()?.medications?.firstOrNull()?.medicationsClasses.orEmpty()
                val medicineViewItem = medicineViewItemMapper.map(medicines)
                withContext(Dispatchers.IO) {
                    localDataSource.insertAll(medicineViewItem)
                }
                medicineViewItem
            }
        }
    }

    suspend fun getMedicineById(medicineId: Int): Medicine =
        localDataSource.getMedicineById(medicineId = medicineId)
}