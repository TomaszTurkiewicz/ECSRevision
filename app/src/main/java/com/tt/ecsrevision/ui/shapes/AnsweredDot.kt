package com.tt.ecsrevision.ui.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.tt.ecsrevision.ui.theme.myColors

@Composable
fun AnsweredDot(
    iconSize: Dp,
    currentQuestion:Boolean,
    answeredQuestions:Boolean
) {
    val frameColor = MaterialTheme.myColors.primaryLight
    val notAnsweredColor = MaterialTheme.myColors.primaryContainer
    val currentQuestionColor = MaterialTheme.myColors.primary

    Canvas(
        modifier = Modifier
            .size(iconSize))
    {
        if(!answeredQuestions){
            drawCircle(
                color = notAnsweredColor,
                style = Fill,
                radius = size.width*0.4f
            )
        }
        if(currentQuestion){
            drawCircle(
                color = currentQuestionColor,
                style = Stroke(width = size.width*0.1f),
                radius = size.width*0.4f
            )
        }else{
            drawCircle(
                color = frameColor,
                style = Stroke(width = size.width*0.1f),
                radius = size.width*0.4f
            )
        }


    }

}