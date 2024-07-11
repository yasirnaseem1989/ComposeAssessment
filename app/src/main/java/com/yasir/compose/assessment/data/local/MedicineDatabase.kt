package com.yasir.compose.assessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yasir.compose.assessment.data.model.Medicine

@Database(entities = [Medicine::class], version = 1)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}