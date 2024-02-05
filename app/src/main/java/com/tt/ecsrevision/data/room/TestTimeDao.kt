package com.tt.ecsrevision.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestTimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(testTime: TestTime)

    @Query("Select * from testtime")
    fun getTestTime(): TestTime

    @Query("DELETE FROM testtime")
    suspend fun deleteTestTime()
}