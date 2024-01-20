package com.tt.ecsrevision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tt.ecsrevision.ui.theme.ECSRevisionTheme
import com.tt.ecsrevision.viewmodels.AppViewModel
import androidx.activity.viewModels
import com.tt.ecsrevision.viewmodels.AppViewModelFactory


class MainActivity : ComponentActivity() {

    private val viewModel:AppViewModel by viewModels{
        AppViewModelFactory(
            (this.application as ECSApplication).database.questionDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECSRevisionTheme {
                ECSRevisionApp(viewModel)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//
//    ECSRevisionTheme {
//        ECSRevisionApp()
//    }
//}


//todo use ksp instead kapt !!!!