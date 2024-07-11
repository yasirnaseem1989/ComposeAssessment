package com.yasir.compose.assessment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey val id: Int,
    val name: String,
    val dose: String,
    val strength: String
)