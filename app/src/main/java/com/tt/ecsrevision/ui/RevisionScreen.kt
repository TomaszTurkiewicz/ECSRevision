package com.tt.ecsrevision.ui

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

@Composable
fun RevisionScreen()
{
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        //question
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "Question",
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
                Text(text = "Answer")
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
                    text = "Answer"
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
                Text(text = "Answer")
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
                Text(text = "Answer")
            }
        }

        // next and previous
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(),
                contentAlignment = Alignment.Center

            ){
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "PREVIOUS",
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = { /*TODO*/ },

                ) {
                    Text(text = "NEXT",
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center)
                }
            }

        }

    }

}


@Preview
@Composable
fun RevisionScreenPreview(){
    RevisionScreen()
}