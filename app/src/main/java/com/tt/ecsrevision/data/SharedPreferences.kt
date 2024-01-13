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
            return sp.getInt(context.getString(R.string.database_number), 100)
        }
    }
}