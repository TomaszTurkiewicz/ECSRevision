package com.tt.ecsrevision.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tt.ecsrevision.data.AppUiState
import com.tt.ecsrevision.data.SharedPreferences
import com.tt.ecsrevision.data.room.PassMark
import com.tt.ecsrevision.data.room.PassMarkDao
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.data.room.QuestionDao
import com.tt.ecsrevision.data.room.Test
import com.tt.ecsrevision.data.room.TestDao
import com.tt.ecsrevision.data.room.TestTime
import com.tt.ecsrevision.data.room.TestTimeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AppViewModel(
    private val questionDao: QuestionDao,
    private val testDao: TestDao,
    private val passMarkDao: PassMarkDao,
    private val testTimeDao: TestTimeDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private var list: MutableList<Question> = mutableListOf()
    private var testList:MutableList<Test> = mutableListOf()
    private var passMark = 0
    private var testTime = 0

    private var currentRevisionQuestion = 0
    private var oneAnswer = false

    private var interstitialAdClicks = 0


    fun getAllQuestions(context:Context){
        val numberOfQuestions = SharedPreferences.getNumberOfQuestions(context)
        coroutineScope.launch(Dispatchers.IO) {
            list = questionDao.getQuestions()
            if(list.size == numberOfQuestions){
                sort()
            }else{
             getAllQuestions(context)
            }
        }
    }

    fun getAllTest(context: Context){
        val numberOfTests = SharedPreferences.getNumberOfTests(context)
        coroutineScope.launch(Dispatchers.IO){
            testList = testDao.getTest()
            if(testList.size == numberOfTests){
                _uiState.update { currentState ->
                    currentState.copy(
                        testReady = true
                    )
                }
            }else{
                getAllTest(context)
            }
        }
    }

    fun getPassMark(){
        coroutineScope.launch(Dispatchers.IO) {
        passMark = passMarkDao.getPassMark()
        _uiState.update { currentState ->
            currentState.copy(
                passMarkReady = true
            )
        }
        }
    }

    fun getTestTime(){
        coroutineScope.launch(Dispatchers.IO) {
            testTime = testTimeDao.getTestTime()
            _uiState.update { currentState ->
                currentState.copy(
                    testTimeReady = true
                )
            }
        }
    }


    fun nukeTable(){
        coroutineScope.launch(Dispatchers.IO){
            questionDao.deleteAllQuestions()
        }
    }

    fun nukeTables(){
        coroutineScope.launch(Dispatchers.IO){
            questionDao.deleteAllQuestions()
            testDao.deleteAllTests()
            testTimeDao.deleteTestTime()
            passMarkDao.deletePassMark()
        }
    }

    fun getClicks():Int{
        return this.interstitialAdClicks
    }

    private fun sort(){
        var needsSorting = false
        for(i in 0 .. list.size-2){
            if(list[i].segment<list[i+1].segment){
                //do nothing
            }
            else if (list[i].segment==list[i+1].segment){
                // check numbers
                if(list[i].number>list[i+1].number){
                    val tmp = list[i]
                    list[i]=list[i+1]
                    list[i+1] = tmp
                    needsSorting=true
                }
            }
            else{
                // replace
                val tmp = list[i]
                list[i]=list[i+1]
                list[i+1] = tmp
                needsSorting=true
            }
        }
        if(needsSorting){
            sort()
        }
        else{
            _uiState.update { currentState ->
                currentState.copy(
                    revisionQuestionsReady = true
                )
            }
        }
    }

    fun insertQuestion(question: Question){
        coroutineScope.launch(Dispatchers.IO){
            questionDao.insert(question = question)
        }
    }

    fun insertTest(test: Test){
        coroutineScope.launch(Dispatchers.IO){
            testDao.insert(test = test)
        }
    }

    fun insertTestTime(testTime: TestTime){
        coroutineScope.launch(Dispatchers.IO) {
            testTimeDao.insert(testTime = testTime)
        }
    }

    fun insertPassMArk(passMark: PassMark){
        coroutineScope.launch(Dispatchers.IO) {
            passMarkDao.insert(passMark = passMark)
        }
    }

    fun getNumberFromSharedPreferences(context: Context){
        val number = SharedPreferences.getDatabaseNumber(context)
        _uiState.update { currentState ->
            currentState.copy(
                databaseVersion = number
            )
        }
    }

    fun getCurrentRevisionQuestionFromSharedPreferences(context: Context){
        currentRevisionQuestion = SharedPreferences.getCurrentRevisionQuestion(context)
        _uiState.update { currentState ->
            currentState.copy(
                question = list[currentRevisionQuestion]
            )
        }
    }

    fun getOneAnswerBooleanFromSharedPreferences(context: Context){
        oneAnswer = SharedPreferences.getOneAnswer(context)
        _uiState.update { currentState ->
            currentState.copy(
                oneAnswer = oneAnswer
            )
        }
    }

    fun invertOneAnswer(context: Context){
        oneAnswer = !oneAnswer
        SharedPreferences.saveOneAnswer(context, oneAnswer)
        _uiState.update { currentState ->
            currentState.copy(
                oneAnswer = oneAnswer
            )
        }
    }

    fun finishTestIntro(){
        _uiState.update { currentState ->
            currentState.copy(
                testIntro = false
            )
        }
    }

    private fun saveCurrentRevisionQuestionToSharedPreferences(context: Context){
        SharedPreferences.saveCurrentRevisionQuestion(context,currentRevisionQuestion)
        getCurrentRevisionQuestionFromSharedPreferences(context)
    }

    fun initializeCurrentRevisionQuestion(context: Context){
        currentRevisionQuestion = 0
        SharedPreferences.saveCurrentRevisionQuestion(context,currentRevisionQuestion)
    }

    fun nextRevisionQuestion(context: Context) {
        if (currentRevisionQuestion < list.size - 1) {
            currentRevisionQuestion += 1
            increaseInterstitialAdClicks()
            saveCurrentRevisionQuestionToSharedPreferences(context)
        }
    }

    fun backToFirstQuestion(context: Context){
        if(currentRevisionQuestion != 0){
            currentRevisionQuestion = 0
            saveCurrentRevisionQuestionToSharedPreferences(context)
        }
    }

    fun previousRevisionQuestion(context: Context){
        if(currentRevisionQuestion > 0){
            currentRevisionQuestion -=1
            increaseInterstitialAdClicks()
            saveCurrentRevisionQuestionToSharedPreferences(context)
        }
    }

    private fun increaseInterstitialAdClicks(){
        if(interstitialAdClicks<40) {
            interstitialAdClicks += 1
        }else{
            interstitialAdClicks =0
        }
    }


    fun saveNumberToSharedPreferences(context:Context, number:Int){
        SharedPreferences.saveDatabaseNumber(context,number)
        _uiState.update { currentState ->
            currentState.copy(
                databaseVersion = number
            )
        }
    }

    fun isCurrentRevisionPositionEqualToZero():Boolean{
        return currentRevisionQuestion == 0
    }

    fun isRevisionLastQuestion():Boolean{
        return currentRevisionQuestion == list.size-1
    }

}

class AppViewModelFactory(
    private val questionDao: QuestionDao,
    private val testDao: TestDao,
    private val passMarkDao: PassMarkDao,
    private val testTimeDao: TestTimeDao
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AppViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(questionDao,testDao,passMarkDao,testTimeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}