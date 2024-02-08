package com.tt.ecsrevision.ui.shapes

import android.graphics.Point
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

@Composable
fun ResetShape(
    iconSize:Dp
)
{
    val color = Color.Black

    val stroke = iconSize*0.05f

    Canvas(modifier = Modifier
        .size(iconSize)
        .padding(iconSize/4)){
        drawArc(
            color,
            120f,
            300f,
            false,
            style = Stroke(stroke.toPx(), cap = StrokeCap.Round),
            size = Size(size.width,size.height)
        )
        val endX = 0.75
        val endY = 0.94
        val dif = (size.width*0.2f)
        val pick = Point((size.width*endX).toInt(), (size.height*endY).toInt())
        val left = Point(pick.x, (pick.y-dif).toInt())
        val path1 = Path()
        path1.apply {
            moveTo(pick.x.toFloat(), pick.y.toFloat())
            lineTo(left.x.toFloat(),left.y.toFloat())
        }
        drawPath(path1, color, style = Stroke(stroke.toPx(), cap = StrokeCap.Round))

        val path2 = Path()
        val right = Point((pick.x+dif).toInt(), pick.y)
        path2.apply {
            moveTo(pick.x.toFloat(), pick.y.toFloat())
            lineTo(right.x.toFloat(), right.y.toFloat())
        }
        drawPath(path2, color, style = Stroke(stroke.toPx(), cap = StrokeCap.Round))
    }
}