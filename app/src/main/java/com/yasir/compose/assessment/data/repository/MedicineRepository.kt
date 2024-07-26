package com.yasir.compose.assessment.data.repository

import com.yasir.compose.assessment.data.local.LocalDataSource
import com.yasir.compose.assessment.data.mapper.MedicineViewItemMapper
import com.yasir.compose.assessment.data.remote.RemoteDataSource
import com.yasir.compose.assessment.data.repository.ErrorType.Generic
import com.yasir.compose.assessment.domain.model.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val medicineViewItemMapper: MedicineViewItemMapper,
) {
    suspend fun getAllMedicines(): Result<List<Medicine>> {
        val medicineData = localDataSource.getAllMedicines()
        if (medicineData.isNotEmpty()) {
            return Result.Success(medicineData)
        }

        return when (val remoteResult = remoteDataSource.getMedicines()) {
            is Result.Success -> {
                val medicines =
                    remoteResult.data.firstOrNull()?.diabetes?.firstOrNull()?.medications?.firstOrNull()?.medicationsClasses.orEmpty()
                val medicineViewItem = medicineViewItemMapper.map(medicines)
                withContext(Dispatchers.IO) {
                    localDataSource.insertAll(medicineViewItem)
                }
                Result.Success(medicineViewItem)
            }
            is Result.Error -> {
                Result.Error(remoteResult.exception)
            }
        }
    }

    suspend fun getMedicineById(medicineId: Int): Result<Medicine> {
        val medicine = localDataSource.getMedicineById(medicineId)
        return if (medicine.hasValidId()) {
            Result.Success(medicine)
        } else {
            Result.Error(Generic("Medicine not found"))
        }
    }
}