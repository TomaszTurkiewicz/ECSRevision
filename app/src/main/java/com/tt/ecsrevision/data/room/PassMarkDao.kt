package com.tt.ecsrevision.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PassMarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(passMark: PassMark)

    @Query("Select * from passmark")
    fun getPassMark(): PassMark

    @Query("DELETE FROM passmark")
    suspend fun deletePassMark()
}