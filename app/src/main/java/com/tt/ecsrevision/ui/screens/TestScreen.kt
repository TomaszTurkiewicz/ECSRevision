package com.tt.ecsrevision.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.tt.ecsrevision.TEST
import com.tt.ecsrevision.data.TEST_INTRO
import com.tt.ecsrevision.data.TEST_RUN
import com.tt.ecsrevision.data.TEST_SUMMARY
import com.tt.ecsrevision.data.TEST_WRONG_ANSWERS
import com.tt.ecsrevision.ui.screens.test.TestIntroScreen
import com.tt.ecsrevision.viewmodels.AppViewModel

@Composable
fun TestScreen(
    viewModel:AppViewModel,
    navController: NavController,
    testState:Int
) {
    when(testState){
        TEST_INTRO -> {
            TestIntroScreen(
                viewModel = viewModel
            )
         }
        TEST_RUN -> {
            // todo take test
        }
        TEST_SUMMARY -> {
            //todo show summary screen asking to review wrong questions
        }
        TEST_WRONG_ANSWERS -> {
            // todo show all wrong questions
        }
    }
}
