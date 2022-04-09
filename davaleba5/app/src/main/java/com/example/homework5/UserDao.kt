package com.example.homework5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertAll(user: User)

    @Query("SELECT AVG(RUN_RANGE) FROM USER")
    suspend fun getAvgDistanceRun(): Double

    @Query("SELECT AVG(SWIM_RANGE) FROM USER")
    suspend fun getAvgDistanceSwim(): Double

    @Query("SELECT AVG(CALORIE) FROM USER")
    suspend fun getAvgCalories(): Double

    @Query("SELECT SUM(RUN_RANGE) FROM USER")
    suspend fun getTotalDistanceRun(): Double
}