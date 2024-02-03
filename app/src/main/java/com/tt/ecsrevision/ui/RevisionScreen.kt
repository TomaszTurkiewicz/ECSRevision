package com.tt.ecsrevision.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.viewmodels.AppViewModel


@Composable
fun RevisionScreen(
    context:Context,
    viewModel: AppViewModel,
    question:Question
) {

    viewModel.getCurrentRevisionQuestionFromSharedPreferences(context)


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        //question number and beginning button
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
                Text(
                    text = "QUESTION: " + question.segment + "." + question.number,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { viewModel.backToFirstQuestion(context) }

                ) {
                    Text(
                        text = "BEGINNING",
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center
                    )
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
                answer = question.answerA
            )
            // answer B
            RevisionAnswerRow(
                modifier = Modifier,
                heightSize = 0.33f,
                answerMark = "B: ",
                answer = question.answerB
            )

            // answer C

            RevisionAnswerRow(
                modifier = Modifier,
                heightSize = 0.5f,
                answerMark = "C: ",
                answer = question.answerC
            )

            // answer D

            RevisionAnswerRow(
                modifier = Modifier,
                heightSize = 1f,
                answerMark = "D: ",
                answer = question.answerD
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
                    Button(onClick = { viewModel.previousRevisionQuestion(context) }) {
                        Text(
                            text = "PREVIOUS",
                            modifier = Modifier.fillMaxWidth(0.5f),
                            textAlign = TextAlign.Center
                        )
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
                    Button(
                        onClick = { viewModel.nextRevisionQuestion(context) }

                    ) {
                        Text(
                            text = "NEXT",
                            modifier = Modifier.fillMaxWidth(0.5f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun RevisionScreenPreview(){
//    RevisionScreen()
//}