package com.tt.ecsrevision.ui.screens.test


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.tt.ecsrevision.R
import com.tt.ecsrevision.helpers.TestQuestion
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.ui.components.CustomButtonWithText
import com.tt.ecsrevision.ui.components.TestAnswerRow
import com.tt.ecsrevision.viewmodels.AppViewModel
import java.nio.file.WatchEvent

@Composable
fun TestRunScreen(
    testQuestion:TestQuestion,
    viewModel: AppViewModel
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        // dots answered / not answered questions and time and finish button
        Row (
             modifier = Modifier
                 .fillMaxWidth()
                 .fillMaxHeight(0.15f)
                 .background(Color.Red)
        ){
            //todo dots for answered / not answered questions and time and finish button
        }


        //Question
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentAlignment = Alignment.Center
        ){
            ComposeAutoResizedText(
                text = testQuestion.question.question,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(0.9f))
        }

        //Answers
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Answer A
            TestAnswerRow(
                heightSize = 0.25f,
                answerMark = context.getString(R.string.a) ,
                answer = testQuestion.question.answerA
            )

            //Answer B
            TestAnswerRow(
                heightSize = 0.33f,
                answerMark = context.getString(R.string.b) ,
                answer = testQuestion.question.answerB
            )

            //Answer C
            TestAnswerRow(
                heightSize = 0.5f,
                answerMark = context.getString(R.string.c) ,
                answer = testQuestion.question.answerC
            )

            //Answer D
            TestAnswerRow(
                heightSize = 1f,
                answerMark = context.getString(R.string.d) ,
                answer = testQuestion.question.answerD
            )
        }

        // next and previous
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            // previous
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                if(viewModel.isPreviousTestQuestion()) {
                    CustomButtonWithText(
                        title = context.getString(R.string.previous),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.5f)
                    ) {
                        viewModel.decreaseTestCounter()
                    }
                }
            }

            // next
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {

                if(!viewModel.isLastTestQuestion()) {

                    CustomButtonWithText(
                        title = context.getString(R.string.next),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.5f)
                    ) {
                        viewModel.increaseTestCounter()
                    }
                }
            }

        }
    }
}