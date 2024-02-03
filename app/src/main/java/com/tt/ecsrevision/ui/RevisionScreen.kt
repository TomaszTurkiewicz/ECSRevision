package com.tt.ecsrevision.ui

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.tt.ecsrevision.R
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.viewmodels.AppViewModel
import java.lang.reflect.Type


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
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f),
                horizontalArrangement = Arrangement.Absolute.Left,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ComposeAutoResizedText(
                    text = "A:",
                    modifier = Modifier.fillMaxWidth(0.1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                ComposeAutoResizedText(
                    text = question.answerA,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            // answer B
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f),
                horizontalArrangement = Arrangement.Absolute.Left,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ComposeAutoResizedText(
                    text = "B:",
                    modifier = Modifier.fillMaxWidth(0.1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                ComposeAutoResizedText(
                    text = question.answerB,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            // answer C
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                horizontalArrangement = Arrangement.Absolute.Left,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ComposeAutoResizedText(
                    text = "C:",
                    modifier = Modifier.fillMaxWidth(0.1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                ComposeAutoResizedText(
                    text = question.answerC,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
            // answer D
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Absolute.Left,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ComposeAutoResizedText(
                    text = "D:",
                    modifier = Modifier.fillMaxWidth(0.1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                ComposeAutoResizedText(
                    text = question.answerD,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
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