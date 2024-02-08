package com.tt.ecsrevision.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.MainActivity
import com.tt.ecsrevision.R
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.ui.alertdialogs.InfoAlertDialog
import com.tt.ecsrevision.ui.alertdialogs.InterstitialAdAlertDialog
import com.tt.ecsrevision.ui.components.ANSWER_SETTINGS_ICON
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.ui.components.ElevatedButtonWithIcon
import com.tt.ecsrevision.ui.components.ElevatedButtonWithText
import com.tt.ecsrevision.ui.components.RESET_ICON
import com.tt.ecsrevision.ui.components.RevisionAnswerRow
import com.tt.ecsrevision.viewmodels.AppViewModel

const val A = 1
const val B = 2
const val C = 3
const val D = 4


@Composable
fun RevisionScreen(
    activity: MainActivity,
    viewModel: AppViewModel,
    question:Question,
    oneAnswer:Boolean
) {

    val context = LocalContext.current
    viewModel.getCurrentRevisionQuestionFromSharedPreferences(context)

    val alertDialogInfo = remember { mutableStateOf(false) }

    val alertDialogAd = remember { mutableStateOf(false) }


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            //question number, settings button and reset button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                ) {
                    ComposeAutoResizedText(
          //              text = "QUESTION: " + question.segment + "." + question.number,
                        text = context.getString(R.string.question_number,question.segment,question.number),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(16.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                        .padding(end = 10.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    ElevatedButtonWithIcon(
                        modifier = Modifier.fillMaxHeight(0.7f),
                        iconType = ANSWER_SETTINGS_ICON,
                        oneAnswer = oneAnswer
                    ) {
                        viewModel.invertOneAnswer(context)
                    }
                }


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(end = 10.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if (!viewModel.isCurrentRevisionPositionEqualToZero()) {
                        ElevatedButtonWithIcon(
                            modifier = Modifier.fillMaxHeight(0.7f),
                            RESET_ICON,
                            false
                        ) {
                            viewModel.backToFirstQuestion(context)
                        }
                    }
                }
            }


            //question
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                contentAlignment = Alignment.Center

            ) {
                ComposeAutoResizedText(
                    text = question.question,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .fillMaxWidth(0.8f),
                    textAlign = TextAlign.Center
                )
            }

            // answers
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // answer A
                RevisionAnswerRow(
                    modifier = Modifier,
                    heightSize = 0.25f,
                    answerMark = context.getString(R.string.a),
                    answer = question.answerA,
                    correctAnswer = question.correctAnswer == A,
                    oneAnswer,
                    question.info,
                    alertDialogInfo
                )
                // answer B
                RevisionAnswerRow(
                    modifier = Modifier,
                    heightSize = 0.33f,
                    answerMark = context.getString(R.string.b),
                    answer = question.answerB,
                    correctAnswer = question.correctAnswer == B,
                    oneAnswer,
                    question.info,
                    alertDialogInfo
                )

                // answer C

                RevisionAnswerRow(
                    modifier = Modifier,
                    heightSize = 0.5f,
                    answerMark = context.getString(R.string.c),
                    answer = question.answerC,
                    correctAnswer = question.correctAnswer == C,
                    oneAnswer,
                    question.info,
                    alertDialogInfo
                )

                // answer D

                RevisionAnswerRow(
                    modifier = Modifier,
                    heightSize = 1f,
                    answerMark = context.getString(R.string.d),
                    answer = question.answerD,
                    correctAnswer = question.correctAnswer == D,
                    oneAnswer,
                    question.info,
                    alertDialogInfo
                )

            }

            // next and previous
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center

                ) {
                    if (!viewModel.isCurrentRevisionPositionEqualToZero()) {

                        ElevatedButtonWithText(
                            text = context.getString(R.string.previous),
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight(0.5f)
                        ) {
                            viewModel.previousRevisionQuestion(context)
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    if (!viewModel.isRevisionLastQuestion()) {

                        ElevatedButtonWithText(
                            text = context.getString(R.string.next),
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight(0.5f)
                        ) {
                            viewModel.nextRevisionQuestion(context)
                        }
                    }
                }
            }
        }

    if(alertDialogInfo.value){
        InfoAlertDialog(
            onDismissRequest = {
                alertDialogInfo.value = false
                               },
            dialogText = question.info,
            context = context
        )

    }

    if(alertDialogAd.value){
        alertDialogAd.value = false
        InterstitialAdAlertDialog(context = context) {

            activity.showInterstitialAd()
        }
    }

    if(viewModel.getClicks()==0){
        activity.loadInterstitialAd()
    }

    if(viewModel.getClicks()==40){
        alertDialogAd.value = activity.interstitialAdIsLoaded()
    }

    }



//@Preview
//@Composable
//fun RevisionScreenPreview(){
//    RevisionScreen()
//}