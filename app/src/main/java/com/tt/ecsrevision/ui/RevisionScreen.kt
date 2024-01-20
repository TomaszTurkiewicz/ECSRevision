package com.tt.ecsrevision.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tt.ecsrevision.data.room.Question
import com.tt.ecsrevision.viewmodels.AppViewModel

@Composable
fun RevisionScreen(
    context:Context,
    viewModel: AppViewModel,
    question:Question
)
{

    viewModel.getCurrentRevisionQuestionFromSharedPreferences(context)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f),
            contentAlignment = Alignment.TopStart){
            Text(text = "" + question.segment + "." + question.number)
        }
        //question
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = question.question,
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
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Text(
                    text = "A:",
                    Modifier
                        .fillMaxWidth(0.1f),
                    textAlign = TextAlign.Center
                )
                Text(text = question.answerA)
            }
            // answer B
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Text(
                    text = "B:",
                    Modifier
                        .fillMaxWidth(0.1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = question.answerB
                )
            }
            // answer C
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Text(
                    text = "C:",
                    Modifier
                        .fillMaxWidth(0.1f),
                    textAlign = TextAlign.Center)
                Text(text = question.answerC)
            }
            // answer D
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Text(
                    text = "D:",
                    Modifier
                        .fillMaxWidth(0.1f),
                    textAlign = TextAlign.Center)
                Text(text = question.answerD)
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

            ){
                if(!viewModel.isCurrentRevisionPositionEqualToZero()){
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
            ){
                if(!viewModel.isRevisionLastQuestion()){
                    Button(
                        onClick = { viewModel.nextRevisionQuestion(context) }

                    ) {
                        Text(text = "NEXT",
                            modifier = Modifier.fillMaxWidth(0.5f),
                            textAlign = TextAlign.Center)
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