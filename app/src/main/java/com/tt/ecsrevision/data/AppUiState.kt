package com.tt.ecsrevision.data

import com.tt.ecsrevision.data.room.Question

data class AppUiState(
    val databaseVersion: Int = 0,
    val question:Question = Question(
        version = 0,
        segment = 0,
        number = 0,
        question = "q",
        answerA = "a",
        answerB = "b",
        answerC = "c",
        answerD = "d",
        correctAnswer = 0,
        info = "i"),
    val revisionQuestionsReady: Boolean = false,
    val oneAnswer:Boolean = false,
    val testIntro:Boolean = true
)