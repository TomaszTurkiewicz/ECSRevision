package com.tt.ecsrevision.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(test:Test)

    @Query("Select * from test")
    fun getTest(): MutableList<Test>

    @Query("Delete from test")
    suspend fun deleteAllTests()
}