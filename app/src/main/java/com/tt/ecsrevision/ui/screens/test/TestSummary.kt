package com.tt.ecsrevision.ui.screens.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tt.ecsrevision.R
import com.tt.ecsrevision.helpers.TimeConverter
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.ui.components.CustomButtonWithText
import com.tt.ecsrevision.ui.theme.myColors
import com.tt.ecsrevision.viewmodels.AppViewModel

@Composable
fun TestSummary(
    viewModel: AppViewModel,
    navController: NavController
) {
    val context = LocalContext.current


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        //title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
            contentAlignment = Alignment.Center
        ){
            ComposeAutoResizedText(
                text = context.getString(R.string.summary),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge)
        }

        // time and score
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
        ) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f),
                contentAlignment = Alignment.Center
            ){
                ComposeAutoResizedText(
                    text = TimeConverter.getTimeWrappedInString(context,R.string.user_test_time,viewModel.getUserTestTime()),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge)

            }

            // score
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center
            ){
                ComposeAutoResizedText(
                    text = context.getString(
                        R.string.you_score,
                        viewModel.getUserScore(),
                        viewModel.getTestNumberOfQuestions()),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge)
            }

            // pass/fail
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                ComposeAutoResizedText(
                    text = if (viewModel.getTestPass()) context.getString(R.string.pass)
                    else context.getString(R.string.failed),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = if(viewModel.getTestPass()) MaterialTheme.myColors.correctAnswerBorder else MaterialTheme.myColors.error
                    )
                )
            }
        }

        // explanation

        viewModel.showFirstWrongQuestion()
        val explanationString:String = if(viewModel.getTestPass()){
            if(viewModel.getAllQuestionsRight()){
                context.getString(R.string.congrats_no_wrong_answers)
            }else{
                context.getString(R.string.congrats_with_wrong_answers, viewModel.getWrongAnswersCounter())
            }
            }else{
                context.getString(R.string.sorry_not_pass, viewModel.getWrongAnswersCounter())
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(4.5f / 6),
            contentAlignment = Alignment.Center
        ){
            ComposeAutoResizedText(
                text = explanationString,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                if(!viewModel.getAllQuestionsRight()){
                    CustomButtonWithText(
                        title = context.getString(R.string.review),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.5f)
                    ) {
                     viewModel.moveToWrongAnswersReview()
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                if(!viewModel.getAllQuestionsRight()){
                    CustomButtonWithText(
                        title = context.getString(R.string.finish),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.5f)
                    ) {
                        navController.navigateUp()
                    }
                }
            }
        }

    }
}