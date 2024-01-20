package com.tt.ecsrevision.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val version:Int,
    val segment:Int,
    val number:Int,
    val question: String,
    val answerA: String,
    val answerB: String,
    val answerC: String,
    val answerD: String,
    val correctAnswer: Int,
    val info: String
)