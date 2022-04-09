package com.example.homework5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "RUN_RANGE") val runRange: Double,
    @ColumnInfo(name = "SWIM_RANGE") val swimRange: Double,
    @ColumnInfo(name = "CALORIE") val calorie: Double
)
