package com.tt.ecsrevision.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.ui.theme.myColors

@Composable
fun WrongAnswerRow(
    heightSize: Float,
    answerMark:String,
    answer:String,
    selected:Boolean,
    correct:Boolean
) {
    val borderColor = if(selected){
        MaterialTheme.myColors.wrongAnswerBorder
    }else if (correct){
        MaterialTheme.myColors.correctAnswerBorder
    }else{
        MaterialTheme.myColors.primaryLight
    }

    val backgroundColor = if(selected){
        MaterialTheme.myColors.wrongAnswerInner
    }else if (correct){
        MaterialTheme.myColors.correctAnswerInner
    }else{
        MaterialTheme.myColors.background
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(heightSize)
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(
                width = 2.dp,
                color = borderColor,
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
        ComposeAutoResizedText(
            text = answer,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth(0.9f)
        )

    }
}