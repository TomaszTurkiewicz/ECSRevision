package com.tt.ecsrevision.ui.components

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.ui.theme.myColors

@Composable
fun RevisionAnswerRow(
    modifier: Modifier,
    heightSize:Float,
    answerMark:String,
    answer:String,
    correctAnswer: Boolean,
    oneAnswer:Boolean,
    info:String,
    alertDialog: MutableState<Boolean>
) {
    val frameColor = if(correctAnswer) MaterialTheme.myColors.correctAnswerBorder else MaterialTheme.colorScheme.secondary
    if(correctAnswer)
    {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(heightSize)
                .padding(5.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.myColors.correctAnswerInner)
                .border(
                    width = 2.dp,
                    color = frameColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(end = 2.dp),
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically
        ){
            ComposeAutoResizedText(
                text = answerMark,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .padding(start = 10.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            ComposeAutoResizedText(
                text = answer,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier
                    .padding(end = 10.dp)
                    .fillMaxWidth(0.9f)
            )

            Box(modifier =Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                contentAlignment = Alignment.Center){

                if(info.isNotEmpty()) {
                    ButtonWithIcon(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        iconType = INFO_ICON,
                        corners = 10.dp,
                        oneAnswer = oneAnswer
                    ) {
                        alertDialog.value = true
                    }
                }
            }


        }
    }
    else
    {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(heightSize)
                .padding(5.dp)
                .border(
                    width = 2.dp,
                    color = frameColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(end = 2.dp),
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ComposeAutoResizedText(
                text = answerMark,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .padding(start = 10.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            if (!oneAnswer) {
                ComposeAutoResizedText(
                    text = answer,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier
                        .padding(end = 10.dp)
                        .fillMaxWidth(0.9f)
                )

            }
        }
    }


}