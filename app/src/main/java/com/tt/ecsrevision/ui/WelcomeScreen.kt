package com.tt.ecsrevision.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.tt.ecsrevision.ECSRevisionScreen
import com.tt.ecsrevision.data.SharedPreferences

@Composable
fun WelcomeScreen(
    context: Context,
    databaseNumber: Int,
    viewModel: AppViewModel,
    navHostController: NavHostController
)
{
    Column {
        Text(
            text = "LOADING",
            modifier = Modifier.clickable {
                viewModel.increaseDatabaseNumber(context)
            }
        )
        Text(
            text = databaseNumber.toString()
        )
    }
}



fun readDatabase(){
    // todo
    //  check database number, compare to existing in memory, if different download new database, when everything ready move to chooser

}