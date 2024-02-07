package com.tt.ecsrevision.ui.screens.test

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.tt.ecsrevision.R
import com.tt.ecsrevision.helpers.TestQuestion
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.ui.components.CustomButtonWithText
import com.tt.ecsrevision.ui.components.WrongAnswerRow
import com.tt.ecsrevision.ui.screens.A
import com.tt.ecsrevision.ui.screens.B
import com.tt.ecsrevision.ui.screens.C
import com.tt.ecsrevision.ui.screens.D
import com.tt.ecsrevision.viewmodels.AppViewModel

@Composable
fun WrongAnswersScreen(
    testQuestion: TestQuestion,
    viewModel: AppViewModel,
    navController: NavController
) {
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        // question counter out of all wrong, finish button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(2f / 3),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                // question counter
                Box(modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    ComposeAutoResizedText(
                        text = context.getString(R.string.wrong_question_counter,viewModel.getCurrentWrongQuestionCounter(),viewModel.getWrongAnswersCounter()),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge)
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    CustomButtonWithText(
                        title = context.getString(R.string.finish),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.6f)
                    ) {
                        navController.navigateUp()
                    }
                }
            }

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
                    .fillMaxWidth(0.9f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            WrongAnswerRow(
                heightSize = 0.25f,
                answerMark = context.getString(R.string.a),
                answer = testQuestion.question.answerA,
                selected = testQuestion.userAnswer==A,
                correct = testQuestion.question.correctAnswer==A
            )
            WrongAnswerRow(
                heightSize = 0.33f,
                answerMark = context.getString(R.string.b),
                answer = testQuestion.question.answerB,
                selected = testQuestion.userAnswer== B,
                correct = testQuestion.question.correctAnswer== B
            )
            WrongAnswerRow(
                heightSize = 0.5f,
                answerMark = context.getString(R.string.c),
                answer = testQuestion.question.answerC,
                selected = testQuestion.userAnswer== C,
                correct = testQuestion.question.correctAnswer==C
            )
            WrongAnswerRow(
                heightSize = 1f,
                answerMark = context.getString(R.string.d),
                answer = testQuestion.question.answerD,
                selected = testQuestion.userAnswer== D,
                correct = testQuestion.question.correctAnswer==D
            )
        }

        //next and previous

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            //previous
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                if (viewModel.isPreviousWrongQuestion()) {
                    CustomButtonWithText(
                        title = context.getString(R.string.previous),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.5f)
                    ) {
                        viewModel.decreaseWrongCounter()
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

                if (!viewModel.isLastWrongQuestion()) {

                    CustomButtonWithText(
                        title = context.getString(R.string.next),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.5f)
                    ) {
                        viewModel.increaseWrongCounter()
                    }
                }
            }
        }
    }
}