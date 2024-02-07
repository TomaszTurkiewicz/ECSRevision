package com.tt.ecsrevision.ui.screens.test

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.MainActivity
import com.tt.ecsrevision.R
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.ui.components.CustomButtonWithText
import com.tt.ecsrevision.viewmodels.AppViewModel
import java.nio.file.WatchEvent

@Composable
fun TestIntroScreen(
    viewModel: AppViewModel,
    activity: MainActivity,
    rewardedAdLoaded:Boolean,
    rewardedAdWatched:Boolean,
    testListReady:Boolean
){
    activity.loadRewardedAd()

    val context = LocalContext.current

    viewModel.prepareUserTest()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        //title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
            contentAlignment = Alignment.Center
        ) {
            ComposeAutoResizedText(
                text = context.getString(R.string.ecs_test),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge)
        }

        // test requirements
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
        ){
// test time
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f),
                contentAlignment = Alignment.Center
            ){
                ComposeAutoResizedText(
                    text = context.getString(R.string.test_time, viewModel.getTestTimeInt()),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge)
            }

            // total questions

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center
            ){
                ComposeAutoResizedText(
                    text = context.getString(R.string.total_questions, viewModel.getTestNumberOfQuestions()),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge)
            }

            // pass mark

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                ComposeAutoResizedText(
//                    text = "Pass mark: "+ viewModel.getPassMarkInt(),
                    text = context.getString(R.string.pass_mark,viewModel.getPassMarkInt()),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge)
            }
        }

        // explanation about questions being prepared and admob being loaded

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(4.5f / 6),
            contentAlignment = Alignment.Center
        )
        {

            ComposeAutoResizedText(
                text = if(rewardedAdWatched && (testListReady)) context.getString(R.string.test_intro_good_luck) else context.getString(R.string.test_intro_explanation),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(10.dp))
        }

        // button to watch admob and start test
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {

            if(rewardedAdWatched && (testListReady)){
                CustomButtonWithText(
                    title = context.getString(R.string.start_test),
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .aspectRatio(3f)
                ) {
                    viewModel.getCurrentTestQuestion()
                    viewModel.goToTest()
                }
            }
            else
            {
            if (rewardedAdLoaded) {
                CustomButtonWithText(
                    title = context.getString(R.string.watch_ad),
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .aspectRatio(3f)
                ) {
                    activity.showRewardedAd()
                }
            } else {
                CustomButtonWithText(
                    title = context.getString(R.string.watch_ad),
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .aspectRatio(3f),
                    visible = false
                ) {
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.ad_not_ready_yet),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
        }
    }
}
