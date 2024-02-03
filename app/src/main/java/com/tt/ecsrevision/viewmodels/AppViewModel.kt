package com.tt.ecsrevision.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tt.ecsrevision.data.AppUiState
import com.tt.ecsrevision.data.SharedPreferences
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.data.room.QuestionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AppViewModel(private val questionDao: QuestionDao) : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private var list: MutableList<Question> = mutableListOf()

    private var currentRevisionQuestion = 0


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



    fun nukeTable(){
        coroutineScope.launch(Dispatchers.IO){
            questionDao.deleteAllQuestions()
        }
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
            saveCurrentRevisionQuestionToSharedPreferences(context)
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

class AppViewModelFactory(private val questionDao: QuestionDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AppViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(questionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}