package com.tt.ecsrevision.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.tt.ecsrevision.data.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()


    fun getNumberFromSharedPreferences(context: Context){
        val number = SharedPreferences.getDatabaseNumber(context)
        _uiState.update { currentState ->
            currentState.copy(
                databaseVersion = number
            )
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

}