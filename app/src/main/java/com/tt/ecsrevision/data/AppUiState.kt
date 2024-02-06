package com.tt.ecsrevision.data

import com.tt.ecsrevision.data.room.Question

const val TEST_INTRO = 0
const val TEST_RUN = 1
const val TEST_SUMMARY = 3
const val TEST_WRONG_ANSWERS = 4
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
    val testReady: Boolean = false,
    val passMarkReady:Boolean = false,
    val testTimeReady:Boolean = false,
    val oneAnswer:Boolean = false,
    val testState:Int = TEST_INTRO,
    val rewardedApLoaded:Boolean = false,
    val rewardedAdWatched:Boolean = false
)