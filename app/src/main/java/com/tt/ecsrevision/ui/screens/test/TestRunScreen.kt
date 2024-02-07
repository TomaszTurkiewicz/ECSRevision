package com.tt.ecsrevision.ui.screens.test


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import com.tt.ecsrevision.R
import com.tt.ecsrevision.helpers.TestQuestion
import com.tt.ecsrevision.helpers.TimeConverter
import com.tt.ecsrevision.ui.alertdialogs.EndOfTimeAlertDialog
import com.tt.ecsrevision.ui.alertdialogs.NotAllAnsweredAlertDialog
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.ui.components.CustomButtonWithText
import com.tt.ecsrevision.ui.components.TestAnswerRow
import com.tt.ecsrevision.ui.screens.A
import com.tt.ecsrevision.ui.screens.B
import com.tt.ecsrevision.ui.screens.C
import com.tt.ecsrevision.ui.screens.D
import com.tt.ecsrevision.ui.shapes.AnsweredDot
import com.tt.ecsrevision.viewmodels.AppViewModel
import kotlinx.coroutines.delay

@Composable
fun TestRunScreen(
    testQuestion:TestQuestion,
    viewModel: AppViewModel,
    selectedAnswer:Int
) {

    val context = LocalContext.current

    val alertDialogNotAllAnswers = remember {
        mutableStateOf(false)
    }

    val alertDialogEndOfTime = remember {
        mutableStateOf(false)
    }

    val time = viewModel.getTestTimeInt()
    val t = time*60+1
    val start = time*60

    val timeLeft = remember {
        mutableStateOf(t)
    }

    val timeString = if(timeLeft.value>start){
        TimeConverter.getClock(start,context)
    } else {
        TimeConverter.getClock(timeLeft.value,context)
    }


    LaunchedEffect(key1 = timeLeft.value){
        if(timeLeft.value==0){
            alertDialogNotAllAnswers.value = false
            alertDialogEndOfTime.value = true
        }
        while(timeLeft.value>0){
            delay(1000L)
            timeLeft.value -=1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        // dots answered / not answered questions and time and finish button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
        ) {

            //time and finish button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(2f / 3),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center)
                {
                    ComposeAutoResizedText(
                        text = timeString,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                )
                {
                  CustomButtonWithText(
                      title = context.getString(R.string.finish),
                      modifier = Modifier
                          .fillMaxWidth(0.8f)
                          .fillMaxHeight(0.6f)) {
                      if(viewModel.allTestAnswered()){
                          viewModel.goToSummary()
                      }else{
                          alertDialogNotAllAnswers.value = true
                      }
                  }
                }
            }

            val middle = if(viewModel.getTestNumberOfQuestions()%2==0){
                (viewModel.getTestNumberOfQuestions() / 2)
            }else{
                (viewModel.getTestNumberOfQuestions() / 2)+1
            }

            val iconSize = remember {
                mutableStateOf(0)
            }
            // not answered questions dots
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .onSizeChanged {
                        iconSize.value = it.width / middle
                    }
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0..<middle) {
                        AnsweredDot(
                            iconSize = with(LocalDensity.current) {
                            iconSize.value.toDp()
                        },
                            currentQuestion = i == viewModel.getCurrentTestQuestionCounter(),
                            answeredQuestions = viewModel.getTestAnsweredQuestion(i)
                            )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in middle ..<viewModel.getTestNumberOfQuestions()) {
                        AnsweredDot(
                            iconSize = with(LocalDensity.current) {
                            iconSize.value.toDp()
                        },
                            currentQuestion = i == viewModel.getCurrentTestQuestionCounter(),
                            answeredQuestions = viewModel.getTestAnsweredQuestion(i)
                        )
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
            ) {
                ComposeAutoResizedText(
                    text = testQuestion.question.question,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .fillMaxWidth(0.9f)
                )
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
                    answerMark = context.getString(R.string.a),
                    answer = testQuestion.question.answerA,
                    selected = selectedAnswer==A
                ){
                    viewModel.selectTestAnswer(A)

                }

                //Answer B
                TestAnswerRow(
                    heightSize = 0.33f,
                    answerMark = context.getString(R.string.b),
                    answer = testQuestion.question.answerB,
                    selected = selectedAnswer == B
                ){
                    viewModel.selectTestAnswer(B)
                }

                //Answer C
                TestAnswerRow(
                    heightSize = 0.5f,
                    answerMark = context.getString(R.string.c),
                    answer = testQuestion.question.answerC,
                    selected = selectedAnswer == C
                ){
                    viewModel.selectTestAnswer(C)
                }

                //Answer D
                TestAnswerRow(
                    heightSize = 1f,
                    answerMark = context.getString(R.string.d),
                    answer = testQuestion.question.answerD,
                    selected = selectedAnswer == D
                ){
                    viewModel.selectTestAnswer(D)
                }
            }

            // next and previous
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // previous
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    if (viewModel.isPreviousTestQuestion()) {
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

                    if (!viewModel.isLastTestQuestion()) {

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
    if(alertDialogNotAllAnswers.value){
        NotAllAnsweredAlertDialog(
            context = context,
            onPositiveClick = {
                viewModel.goToSummary()
            }) {
            alertDialogNotAllAnswers.value = false
        }
    }

    if(alertDialogEndOfTime.value){
        EndOfTimeAlertDialog(
            context
        ) {
            alertDialogEndOfTime.value = false
            viewModel.goToSummary()
        }
    }

    }