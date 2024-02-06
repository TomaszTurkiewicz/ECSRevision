package com.tt.ecsrevision.helpers

import com.tt.ecsrevision.data.room.Question

class TestQuestion(val question: Question, var userAnswer: Int = 0) {

    fun getUserAnsweredTheQuestion():Boolean{
        return this.userAnswer != 0
    }

    fun setUserAnswerCurrent(answer:Int){
        this.userAnswer = answer
    }
}