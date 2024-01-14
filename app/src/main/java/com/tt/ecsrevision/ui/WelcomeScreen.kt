package com.tt.ecsrevision.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.snap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tt.ecsrevision.ECSRevisionScreen
import com.tt.ecsrevision.R

@Composable
fun WelcomeScreen(
    context: Context,
    databaseNumber: Int,
    viewModel: AppViewModel,
    moveToNext: () -> Unit
)
{

    Column {
        Text(
            text = "LOADING"
        )
        Text(
            text = databaseNumber.toString()
        )
    }

    LaunchedEffect(key1 = 1){
        checkDatabase(context, databaseNumber,viewModel){
            moveToNext()
        }
    }



}



fun checkDatabase(context: Context,spNumber:Int, viewModel: AppViewModel, moveToNext: () -> Unit){
    val db = Firebase.database.getReference(context.getString(R.string.firebase_database_number))
    db.addListenerForSingleValueEvent(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val fNumber = snapshot.getValue(Int::class.java)
            if(fNumber!=null){
                if(fNumber==spNumber){
                    moveToNext()
                }else{
                    viewModel.saveNumberToSharedPreferences(context,fNumber)
                    //todo create database with new number
                    moveToNext()
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