package com.tt.ecsrevision.ui.screens

import android.content.Context
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.ui.components.ANSWER_SETTINGS_ICON
import com.tt.ecsrevision.ui.components.ButtonWithIcon
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.ui.components.CustomButtonWithText
import com.tt.ecsrevision.ui.components.INFO_ICON
import com.tt.ecsrevision.ui.components.InfoAlertDialog
import com.tt.ecsrevision.ui.components.RESET_ICON
import com.tt.ecsrevision.ui.components.RevisionAnswerRow
import com.tt.ecsrevision.viewmodels.AppViewModel


@Composable
fun RevisionScreen(
    context:Context,
    viewModel: AppViewModel,
    question:Question,
    oneAnswer:Boolean
) {

    viewModel.getCurrentRevisionQuestionFromSharedPreferences(context)

    val alertDialog = remember { mutableStateOf(false) }

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
                        text = "QUESTION: " + question.segment + "." + question.number,
                        textAlign = TextAlign.Start,
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
                    ButtonWithIcon(
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
                        ButtonWithIcon(
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
                    answerMark = "A: ",
                    answer = question.answerA,
                    correctAnswer = question.correctAnswer == 1,
                    oneAnswer,
                    question.info,
                    alertDialog
                )
                // answer B
                RevisionAnswerRow(
                    modifier = Modifier,
                    heightSize = 0.33f,
                    answerMark = "B: ",
                    answer = question.answerB,
                    correctAnswer = question.correctAnswer == 2,
                    oneAnswer,
                    question.info,
                    alertDialog
                )

                // answer C

                RevisionAnswerRow(
                    modifier = Modifier,
                    heightSize = 0.5f,
                    answerMark = "C: ",
                    answer = question.answerC,
                    correctAnswer = question.correctAnswer == 3,
                    oneAnswer,
                    question.info,
                    alertDialog
                )

                // answer D

                RevisionAnswerRow(
                    modifier = Modifier,
                    heightSize = 1f,
                    answerMark = "D: ",
                    answer = question.answerD,
                    correctAnswer = question.correctAnswer == 4,
                    oneAnswer,
                    question.info,
                    alertDialog
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

                        CustomButtonWithText(
                            title = "PREVIOUS",
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

                        CustomButtonWithText(
                            title = "NEXT",
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

    if(alertDialog.value){
        InfoAlertDialog(
            onDismissRequest = {
                alertDialog.value = false
                               },
            dialogText = question.info
        )

    }
    }


//@Preview
//@Composable
//fun RevisionScreenPreview(){
//    RevisionScreen()
//}