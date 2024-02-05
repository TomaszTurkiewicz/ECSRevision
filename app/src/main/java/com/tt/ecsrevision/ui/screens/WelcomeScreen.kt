package com.tt.ecsrevision.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tt.ecsrevision.R
import com.tt.ecsrevision.data.SharedPreferences
import com.tt.ecsrevision.data.firebase.QuestionFirebase
import com.tt.ecsrevision.data.firebase.SegmentTestFirebase
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.data.room.Test
import com.tt.ecsrevision.helpers.DatabaseConverter
import com.tt.ecsrevision.viewmodels.AppViewModel

var currentPosition = 0
var currentTestPosition = 0
var revisionDB = false
var testDB = false
@Composable
fun WelcomeScreen(
    context: Context,
    databaseNumber: Int,
    viewModel: AppViewModel,
    databaseRevisionReady:Boolean,
    databaseTestReady:Boolean,
    moveToNext: () -> Unit
)
{


    if(databaseRevisionReady && (databaseTestReady)){
        moveToNext()
    }else {

        Column {
            Text(
                text = "LOADING"
            )
            Text(
                text = databaseNumber.toString()
            )
        }

        LaunchedEffect(key1 = 1) {
            checkDatabase(context, databaseNumber, viewModel)
        }

    }

}



fun checkDatabase(context: Context, spNumber:Int, viewModel: AppViewModel){
    val db = Firebase.database.getReference(context.getString(R.string.firebase_database_number))
    db.addListenerForSingleValueEvent(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val fNumber = snapshot.getValue(Int::class.java)
            if(fNumber!=null){
                if(fNumber==spNumber){
                    viewModel.getAllQuestions(context)
                    viewModel.getAllTest(context)
                    // for creating question template
//                    createQuestions(context,2)
                    // create test requirements database in Firebase
//                        createTest(context,2)
                }else{


//                    viewModel.nukeTable()
                    viewModel.nukeTables()

                    val list = arrayListOf<Question>()
                    val dbQuestions = Firebase.database.getReference(
                        context.getString(R.string.firebase_database)
                    )
                        .child(fNumber.toString())
                    dbQuestions.addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.exists()){
                                list.clear()
                                for(segment in snapshot.children){
                                    for(question in segment.children){
                                        val q = question.getValue(QuestionFirebase::class.java)
                                        q?.let {
                                            val qr = DatabaseConverter.firebaseToRoom(it,fNumber)
                                            list.add(qr)
                                        }
                                    }
                                }

                                SharedPreferences.saveNumberOfQuestions(context,list.size)

                                saveToRoomDatabase(viewModel,context,fNumber,list.size,list)

                            }else{
                                Toast.makeText(context,context.getString(R.string.failed_to_get_data),Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(context,context.getString(R.string.failed_to_get_data),Toast.LENGTH_LONG).show()
                        }
                    })

                    val testList = arrayListOf<Test>()
                    val dbTest = Firebase.database.getReference(
                        context.getString(R.string.firebase_database_test)
                    )
                        .child(fNumber.toString())
                    dbTest.addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.exists()){
                                testList.clear()
                                for(segment in snapshot.children){
                                    val t = segment.getValue(SegmentTestFirebase::class.java)
                                    t?.let {
                                        val sr = DatabaseConverter.testFirebaseToRoom(it)
                                        testList.add(sr)
                                    }
                                }

                                SharedPreferences.saveNumberOfTests(context,testList.size)

                                saveTestToRoomDatabase(viewModel,context,fNumber,testList.size,testList)
                            }
                            else{
                                Toast.makeText(context,context.getString(R.string.failed_to_get_data),Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(context,context.getString(R.string.failed_to_get_data),Toast.LENGTH_LONG).show()
                        }

                    })

                }
            }else{
                Toast.makeText(context,context.getString(R.string.failed_to_get_data),Toast.LENGTH_LONG).show()
            }
        }




        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context,context.getString(R.string.failed_to_get_data),Toast.LENGTH_LONG).show()
        }

    })
}

private fun saveTestToRoomDatabase(viewModel:AppViewModel, context: Context, fNumber:Int, size:Int, list:ArrayList<Test>) {
    if(currentTestPosition != size){
        viewModel.insertTest(list[currentTestPosition])
        currentTestPosition +=1
        saveTestToRoomDatabase(viewModel,context,fNumber,size,list)
    }else{
        testDB = true
        saveDatabaseNumber(viewModel,context,fNumber)
        viewModel.getAllTest(context)
    }
}

private fun saveToRoomDatabase(viewModel:AppViewModel, context: Context, fNumber:Int, size:Int, list:ArrayList<Question>) {

    if(currentPosition !=size){
        viewModel.insertQuestion(list[currentPosition])
        currentPosition +=1
        saveToRoomDatabase(viewModel,context,fNumber,size,list)
    }else{
        revisionDB = true
        saveDatabaseNumber(viewModel,context,fNumber)
        viewModel.getAllQuestions(context)
        viewModel.initializeCurrentRevisionQuestion(context)
    }
}

private fun saveDatabaseNumber(viewModel:AppViewModel,context: Context,databaseVersion: Int) {
    if (revisionDB && (testDB)) {
        viewModel.saveNumberToSharedPreferences(context, databaseVersion)
    }
}



private fun createTest(context: Context, databaseVersion: Int) {
createTestSegment(context,databaseVersion,1,6)
createTestSegment(context,databaseVersion,2,4)
createTestSegment(context,databaseVersion,3,3)
createTestSegment(context,databaseVersion,4,4)
createTestSegment(context,databaseVersion,5,3)
createTestSegment(context,databaseVersion,6,9)
createTestSegment(context,databaseVersion,7,5)
createTestSegment(context,databaseVersion,8,4)
createTestSegment(context,databaseVersion,9,3)
createTestSegment(context,databaseVersion,10,6)
createTestSegment(context,databaseVersion,11,3)

}

private fun createQuestions(context: Context, databaseVersion:Int) {
    createSegment(context, databaseVersion,1,40)
    createSegment(context,databaseVersion,2,27)
    createSegment(context,databaseVersion,3,32)
    createSegment(context,databaseVersion,4,28)
    createSegment(context,databaseVersion,5,27)
    createSegment(context,databaseVersion,6,38)
    createSegment(context,databaseVersion,7,28)
    createSegment(context,databaseVersion,8,24)
    createSegment(context,databaseVersion,9,16)
    createSegment(context,databaseVersion,10,36)
    createSegment(context,databaseVersion,11,21)


}

private fun createTestSegment(context: Context, databaseVersion: Int, segmentNumber:Int, numberOfQuestions:Int){
    val segmentTest = SegmentTestFirebase()
    segmentTest.segment = segmentNumber
    segmentTest.numberOfQuestions = numberOfQuestions
    val dbTest = Firebase.database.getReference(
        context.getString(R.string.firebase_database_test
        )
    )
        //database version
        .child(databaseVersion.toString())
        // segment number
        .child(segmentNumber.toString())
    dbTest.setValue(segmentTest)
}

private fun createSegment(context:Context, databaseVersion:Int, segmentNumber:Int,numberOfQuestions:Int){
    for(i in 1..numberOfQuestions){
        //TEST
        val questionFirebase = QuestionFirebase()
        questionFirebase.segment = segmentNumber
        questionFirebase.number = i
        questionFirebase.answerA = "A"
        questionFirebase.answerB = "B"
        questionFirebase.answerC = "C"
        questionFirebase.answerD = "D"
        questionFirebase.correctAnswer = 0
        questionFirebase.info = "info"
        questionFirebase.question = "question"

        val dbQuestions = Firebase.database.getReference(
            context.getString(R.string.firebase_database
            )
        )
            .child(
                //database version
                databaseVersion.toString()
            )
            .child(
                // segment number
                questionFirebase.segment.toString()
            ).
            child(
                // question in segment number
                questionFirebase.number.toString()
            )
        dbQuestions.setValue(questionFirebase)

    }
}