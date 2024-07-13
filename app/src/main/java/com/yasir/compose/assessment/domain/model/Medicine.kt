package com.yasir.compose.assessment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dose: String,
    val strength: String
) {
    fun hasValidId(): Boolean = id > 0
}
