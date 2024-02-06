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
import com.tt.ecsrevision.MainActivity
import com.tt.ecsrevision.TEST
import com.tt.ecsrevision.data.TEST_INTRO
import com.tt.ecsrevision.data.TEST_RUN
import com.tt.ecsrevision.data.TEST_SUMMARY
import com.tt.ecsrevision.data.TEST_WRONG_ANSWERS
import com.tt.ecsrevision.helpers.TestQuestion
import com.tt.ecsrevision.ui.screens.test.TestIntroScreen
import com.tt.ecsrevision.ui.screens.test.TestRunScreen
import com.tt.ecsrevision.ui.screens.test.TestSummary
import com.tt.ecsrevision.viewmodels.AppViewModel

@Composable
fun TestScreen(
    viewModel:AppViewModel,
    navController: NavController,
    testState:Int,
    activity: MainActivity,
    rewardedAdLoaded:Boolean,
    rewardedAdWatched:Boolean,
    testListReady:Boolean,
    testQuestion:TestQuestion,
    selectedAnswer:Int
) {

    when(testState){
        TEST_INTRO -> {
            TestIntroScreen(
                viewModel = viewModel,
                activity = activity,
                rewardedAdLoaded = rewardedAdLoaded,
                rewardedAdWatched = rewardedAdWatched,
                testListReady = testListReady
            )
         }
        TEST_RUN -> {
            TestRunScreen(
                testQuestion = testQuestion,
                viewModel = viewModel,
                selectedAnswer = selectedAnswer
            )
        }
        TEST_SUMMARY -> {
            TestSummary()
        }
        TEST_WRONG_ANSWERS -> {
            // todo show all wrong questions
        }
    }
}
