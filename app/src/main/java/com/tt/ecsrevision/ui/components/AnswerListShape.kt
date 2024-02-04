package com.tt.ecsrevision.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.unit.Dp
import com.tt.ecsrevision.ui.theme.myColors

@Composable
fun AnswerListShape(
    iconSize: Dp,
    oneAnswer:Boolean
) {
    val color = MaterialTheme.myColors.background
    val offsetDotX = 0.2f


    Canvas(modifier = Modifier
        .size(iconSize)
    )
    {
        val stroke = size.width*0.1f

        drawCircle(
            color = color,
            radius = size.width*0.05f,
            style = Fill,
            center = Offset(size.width*offsetDotX,size.height*0.2f)
        )

        drawCircle(
            color = color,
            radius = size.width*0.05f,
            style = Fill,
            center = Offset(size.width*offsetDotX,size.height*0.4f)
        )

        drawCircle(
            color = color,
            radius = size.width*0.05f,
            style = Fill,
            center = Offset(size.width*offsetDotX,size.height*0.6f)
        )

        drawCircle(
            color = color,
            radius = size.width*0.05f,
            style = Fill,
            center = Offset(size.width*offsetDotX,size.height*0.8f)
        )

        if(oneAnswer) {
            drawLine(
                color = color,
                start = Offset(size.width * 0.4f, size.height * 0.2f),
                end = Offset(size.width * 0.8f, size.height * 0.2f),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }

        drawLine(
            color = color,
            start = Offset(size.width*0.4f,size.height*0.4f),
            end = Offset(size.width*0.8f,size.height*0.4f),
            strokeWidth = stroke,
            cap = StrokeCap.Round
        )

        if(oneAnswer) {
            drawLine(
                color = color,
                start = Offset(size.width * 0.4f, size.height * 0.6f),
                end = Offset(size.width * 0.8f, size.height * 0.6f),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }

            if(oneAnswer) {
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.4f, size.height * 0.8f),
                    end = Offset(size.width * 0.8f, size.height * 0.8f),
                    strokeWidth = stroke,
                    cap = StrokeCap.Round
                )
            }
    }
}