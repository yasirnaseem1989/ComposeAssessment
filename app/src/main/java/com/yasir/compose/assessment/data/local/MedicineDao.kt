package com.yasir.compose.assessment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yasir.compose.assessment.domain.model.Medicine

@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): List<Medicine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(medicines: List<Medicine>)

    @Query("SELECT * FROM medicines WHERE id = :medicineId")
    fun getMedicineById(medicineId: Int): Medicine
}