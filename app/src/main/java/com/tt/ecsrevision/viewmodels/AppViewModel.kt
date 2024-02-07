package com.tt.ecsrevision.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tt.ecsrevision.data.AppUiState
import com.tt.ecsrevision.data.SharedPreferences
import com.tt.ecsrevision.data.TEST_INTRO
import com.tt.ecsrevision.data.TEST_RUN
import com.tt.ecsrevision.data.TEST_SUMMARY
import com.tt.ecsrevision.data.TEST_WRONG_ANSWERS
import com.tt.ecsrevision.data.room.PassMark
import com.tt.ecsrevision.data.room.PassMarkDao
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.data.room.QuestionDao
import com.tt.ecsrevision.data.room.Test
import com.tt.ecsrevision.data.room.TestDao
import com.tt.ecsrevision.data.room.TestTime
import com.tt.ecsrevision.data.room.TestTimeDao
import com.tt.ecsrevision.helpers.TestQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random


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
    private val userTest:MutableList<TestQuestion> = mutableListOf()
    private var passMark = PassMark()
    private var testTime = TestTime()

    private var userWrongAnswersList:MutableList<TestQuestion> = mutableListOf()


    private var userTestTimeLeft = 0

    private var currentRevisionQuestion = 0
    private var oneAnswer = false

    private var currentTestQuestion = 0
    private var currentWrongQuestion = 0

    private var interstitialAdClicks = 0

    private var userScore = 0
    fun getUserScore():Int {
        userWrongAnswersList = userTest.filter {
            it.question.correctAnswer != it.userAnswer
        }.toMutableList()
        userScore = userTest.size-userWrongAnswersList.size
        return userScore
    }

    fun getUserTestTime():Int{
        val fullTime = testTime.testTime * 60
        return fullTime-userTestTimeLeft
    }

    fun saveUserTestTimeLeft(timeLeft:Int){
        this.userTestTimeLeft = timeLeft
    }
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

    fun prepareUserTest(){
        userTest.clear()
        val temporaryList:MutableList<TestQuestion> = mutableListOf()
        testList.forEach{testListElement ->
            val numberOfQuestionsInSegment = testListElement.numberOfQuestions
            val segment = testListElement.segment
            val segmentList = list.filter {
                listItem -> listItem.segment == segment
            }
                .toMutableList()
            for(i in 0..<numberOfQuestionsInSegment){
                val random = Random.nextInt(segmentList.size)
                temporaryList.add(TestQuestion(segmentList[random]))
                segmentList.removeAt(random)
            }
        }

        while (temporaryList.isNotEmpty()){
            val random = Random.nextInt(temporaryList.size)
            userTest.add(temporaryList[random])
            temporaryList.removeAt(random)
        }

        _uiState.update { currentState ->
            currentState.copy(
                testListReady = true
            )
        }

    }


    fun moveToWrongAnswersReview() {
        _uiState.update { currentState ->
            currentState.copy(
                testState = TEST_WRONG_ANSWERS
            )
        }
    }
    fun getTestAnsweredQuestion(position:Int):Boolean{
        return userTest[position].getUserAnsweredTheQuestion()
    }

    fun selectTestAnswer(answer:Int){
        val a = userTest[currentTestQuestion].userAnswer
        if(a == answer){
            userTest[currentTestQuestion].setUserAnswerCurrent(0)
        }else{
            userTest[currentTestQuestion].setUserAnswerCurrent(answer)
        }

        _uiState.update { currentState ->
            currentState.copy(
                testQuestion = TestQuestion(
                    userTest[currentTestQuestion].question,
                    userTest[currentTestQuestion].userAnswer
                )
            )
        }
    }

    fun allTestAnswered():Boolean{
        var allAnswered = true
        for (i in userTest.indices){
            if(userTest[i].userAnswer==0){
                allAnswered = false
            }
        }
        return allAnswered
    }

    fun getTestTimeInt():Int{
        return testTime.testTime
    }

    fun rewardedApLoaded(){
        _uiState.update { currentState ->
            currentState.copy(
                rewardedApLoaded = true
            )
        }
    }

    fun rewardedAdWatched(){
        _uiState.update { currentState ->
            currentState.copy(
                rewardedAdWatched = true
            )
        }
    }

    fun getCurrentTestQuestionCounter():Int{
        return this.currentTestQuestion
    }

    fun getTestNumberOfQuestions():Int{
        var sum = 0
        testList.forEach {
            sum += it.numberOfQuestions
        }
        return sum
    }

    fun getTestPass():Boolean{
        return userScore>=passMark.passMark
    }

    fun getAllQuestionsRight():Boolean{
        return userScore==userTest.size
    }

    fun getPassMarkInt():Int{
        return this.passMark.passMark
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

    @SuppressLint("SuspiciousIndentation")
    fun getTestTime(){
        coroutineScope.launch(Dispatchers.IO) {
            testTime = testTimeDao.getTestTime()
            checkTestTimeReady()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getPassMark(){
        coroutineScope.launch(Dispatchers.IO) {
            passMark = passMarkDao.getPassMark()
            checkPassMarkReady()
        }
    }

    private fun checkPassMarkReady() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                passMark.passMark
                _uiState.update { currentState ->
                    currentState.copy(
                        passMarkReady = true
                    )
                }
            }catch (e:Exception){
                delay(100L)
                checkPassMarkReady()
            }
        }
    }

    private fun checkTestTimeReady() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                testTime.testTime
                _uiState.update { currentState ->
                    currentState.copy(
                        testTimeReady = true
                    )
                }
            }catch (e:Exception){
                checkTestTimeReady()
            }
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

    fun goToTest() {
       _uiState.update { currentState ->
           currentState.copy(
               testState = TEST_RUN
           )
       }
    }

    fun goToSummary(){
        _uiState.update { currentState ->
            currentState.copy(
                testState = TEST_SUMMARY
            )
        }
    }

    fun resetTest() {
        currentTestQuestion = 0
        _uiState.update { currentState ->
            currentState.copy(
                testState = TEST_INTRO,
                rewardedAdWatched = false,
                rewardedApLoaded = false,
                testListReady = false
            )
        }
    }

    fun getCurrentTestQuestion(){
        _uiState.update { currentState ->
            currentState.copy(
                testQuestion = userTest[currentTestQuestion]
            )
        }
    }

    fun isPreviousTestQuestion():Boolean{
        return currentTestQuestion != 0
    }

    fun isPreviousWrongQuestion():Boolean{
        return currentWrongQuestion != 0
    }

    fun isLastTestQuestion():Boolean{
        return currentTestQuestion == userTest.size-1
    }

    fun isLastWrongQuestion():Boolean{
        return currentWrongQuestion == userWrongAnswersList.size-1
    }

    fun increaseTestCounter(){
        if(currentTestQuestion<userTest.size-1){
            currentTestQuestion +=1
            _uiState.update { currentState ->
                currentState.copy(
                    testQuestion = userTest[currentTestQuestion]
                )
            }
        }
    }

    fun increaseWrongCounter(){
        if(currentWrongQuestion<userWrongAnswersList.size-1){
            currentWrongQuestion +=1
            _uiState.update { currentState ->
                currentState.copy(
                    testQuestion = userWrongAnswersList[currentWrongQuestion]
                )
            }
        }
    }

    fun decreaseTestCounter(){
        if(currentTestQuestion>0){
            currentTestQuestion -=1
            _uiState.update { currentState ->
                currentState.copy(
                    testQuestion = userTest[currentTestQuestion]
                )
            }
        }
    }

    fun decreaseWrongCounter(){
        if(currentWrongQuestion>0){
            currentWrongQuestion -=1
            _uiState.update { currentState ->
                currentState.copy(
                    testQuestion = userWrongAnswersList[currentWrongQuestion]
                )
            }
        }
    }

    fun showFirstWrongQuestion() {
        this.currentWrongQuestion = 0
        _uiState.update { currentState ->
            currentState.copy(
                testQuestion = userWrongAnswersList[currentWrongQuestion]
            )
        }
    }

    fun getWrongAnswersCounter(): Int {
        return userWrongAnswersList.size

    }

    fun getCurrentWrongQuestionCounter(): Int {
        return this.currentWrongQuestion+1
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
            return AppViewModel(questionDao = questionDao,
                testDao = testDao,
                passMarkDao = passMarkDao,
                testTimeDao = testTimeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}