package com.tt.ecsrevision.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Test (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val segment:Int = 0,
    val numberOfQuestions:Int = 0
)