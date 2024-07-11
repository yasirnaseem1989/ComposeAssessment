package com.yasir.compose.assessment.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yasir.compose.assessment.data.model.Medicine
import com.yasir.compose.assessment.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MedicineDao {

    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): Flow<ResultWrapper<List<Medicine>>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(medicines: List<Medicine>)
}