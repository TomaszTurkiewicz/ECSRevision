package com.tt.ecsrevision.data.room

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: Question)

    @Query("Select * from question")
    fun getQuestions(): MutableList<Question>

    @Query("DELETE FROM question")
    suspend fun deleteAllQuestions()
}