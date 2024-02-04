package com.tt.ecsrevision.ui.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.tt.ecsrevision.ui.theme.myColors

@Composable
fun InfoIconShape(
    iconSize: Dp
) {
        val color = MaterialTheme.myColors.background

    Canvas(modifier = Modifier
        .size(iconSize)){
        drawCircle(
            color = color,
            style = Stroke(width = size.width*0.05f),
            radius = size.width*0.4f
        )

        drawLine(
            color = color,
            strokeWidth = size.width*0.05f,
            cap = StrokeCap.Round,
            start = Offset(size.width*0.5f,size.height*0.75f),
            end = Offset(size.width*0.5f,size.height*0.45f)
        )

        drawLine(
            color = color,
            strokeWidth = size.width*0.05f,
            cap = StrokeCap.Round,
            start = Offset(size.width*0.47f,size.height*0.75f),
            end = Offset(size.width*0.53f,size.height*0.75f)
        )

        drawLine(
            color = color,
            strokeWidth = size.width*0.05f,
            cap = StrokeCap.Round,
            start = Offset(size.width*0.5f,size.height*0.45f),
            end = Offset(size.width*0.47f,size.height*0.48f)
        )

        drawCircle(
            color = color,
            style = Fill,
            radius = size.width*0.05f,
            center = Offset(size.width*0.5f,size.height*0.3f)
        )
    }
}