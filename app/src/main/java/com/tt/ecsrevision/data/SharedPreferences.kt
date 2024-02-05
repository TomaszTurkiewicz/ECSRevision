package com.tt.ecsrevision.data

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tt.ecsrevision.R

class SharedPreferences() {
    companion object{

        fun saveDatabaseNumber(context: Context, number:Int){
            val sp = context.getSharedPreferences(context.getString(R.string.database_number),Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putInt(context.getString(R.string.database_number),number)
            editor.apply()
        }

        fun getDatabaseNumber(context: Context): Int {
            val sp = context.getSharedPreferences(
                context.getString(R.string.database_number),
                Context.MODE_PRIVATE
            )
            return sp.getInt(context.getString(R.string.database_number), 0)
        }

        fun saveCurrentRevisionQuestion(context: Context, number:Int){
            val sp = context.getSharedPreferences(context.getString(R.string.revision_question_number),Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putInt(context.getString(R.string.revision_question_number),number)
            editor.apply()
        }

        fun getCurrentRevisionQuestion(context:Context):Int{
            val sp = context.getSharedPreferences(
                context.getString(R.string.revision_question_number),
                Context.MODE_PRIVATE
            )
            return sp.getInt(context.getString(R.string.revision_question_number),0)
        }

        fun saveNumberOfQuestions(context:Context, number: Int){
            val sp = context.getSharedPreferences(context.getString(R.string.number_of_questions),Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putInt(context.getString(R.string.number_of_questions),number)
            editor.apply()
        }

        fun saveNumberOfTests(context:Context, number: Int){
            val sp = context.getSharedPreferences(context.getString(R.string.number_of_tests),Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putInt(context.getString(R.string.number_of_tests),number)
            editor.apply()
        }

        fun getNumberOfQuestions(context: Context): Int {
            val sp = context.getSharedPreferences(
                context.getString(R.string.number_of_questions),
                Context.MODE_PRIVATE
            )
            return sp.getInt(context.getString(R.string.number_of_questions), 0)
        }

        fun getNumberOfTests(context: Context): Int {
            val sp = context.getSharedPreferences(
                context.getString(R.string.number_of_tests),
                Context.MODE_PRIVATE
            )
            return sp.getInt(context.getString(R.string.number_of_tests), 0)
        }

        fun saveOneAnswer(context: Context,oneAnswer:Boolean){
            val sp = context.getSharedPreferences(context.getString(R.string.one_answer),Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putBoolean(context.getString(R.string.one_answer),oneAnswer)
            editor.apply()
        }

        fun getOneAnswer(context: Context):Boolean{
            val sp = context.getSharedPreferences(
                context.getString(R.string.one_answer),
                Context.MODE_PRIVATE
            )
            return sp.getBoolean(context.getString(R.string.one_answer),false)
        }
    }
}