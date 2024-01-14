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
            .fillMaxWidth()
            .background(Color.Green),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        //question
        Box(
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "Question",
                textAlign = TextAlign.Center,
                modifier = Modifier.background(Color.LightGray)
            )
        }

        // answers and buttons
        Column(
            Modifier
                .background(Color.Cyan)
                .fillMaxWidth()
                .fillMaxHeight(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // answer A and C
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Answer A",
                    Modifier.background(Color.Magenta))
                Text(text = "Answer C")
            }
            // answer B and D
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Answer B")
                Text(text = "Answer D")
            }
            // next and previous
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "PREVIOUS")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "NEXT")
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