package com.yasir.compose.assessment.data.repository

import com.yasir.compose.assessment.data.local.LocalDataSource
import com.yasir.compose.assessment.data.model.Medicine
import com.yasir.compose.assessment.data.remote.RemoteDataSource
import com.yasir.compose.assessment.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MedicineRepository {

    override suspend fun getAllMedicines(): Flow<ResultWrapper<List<Medicine>>> {
        return try {
            val remoteMedicines = remoteDataSource.getMedicines()
            /*localDataSource.insertAll(remoteMedicines)*/
            remoteMedicines
        } catch (e: Exception) {
            // If remote fetch fails, return cached data
            localDataSource.getAllMedicines()
        }
    }

    override suspend fun insertAll(medicines: List<Medicine>) {
        localDataSource.insertAll(medicines)
    }
}