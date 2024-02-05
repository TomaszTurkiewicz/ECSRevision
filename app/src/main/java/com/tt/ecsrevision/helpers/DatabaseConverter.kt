package com.tt.ecsrevision.helpers

import com.tt.ecsrevision.data.firebase.QuestionFirebase
import com.tt.ecsrevision.data.firebase.SegmentTestFirebase
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.data.room.Test

class DatabaseConverter {
    companion object{
        fun firebaseToRoom(questionFirebase: QuestionFirebase, version:Int):Question{
            return Question(
                version = version,
                info = questionFirebase.info,
                answerA = questionFirebase.answerA,
                answerB = questionFirebase.answerB,
                answerC = questionFirebase.answerC,
                answerD = questionFirebase.answerD,
                correctAnswer = questionFirebase.correctAnswer,
                segment = questionFirebase.segment,
                number = questionFirebase.number,
                question = questionFirebase.question
            )
        }

        fun testFirebaseToRoom(segmentFirebase:SegmentTestFirebase):Test{
            return Test(
                segment = segmentFirebase.segment,
                numberOfQuestions = segmentFirebase.numberOfQuestions
            )
        }
    }
}