package com.tt.ecsrevision.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ResetShape(
    iconSize:Dp
)
{
    Canvas(modifier = Modifier.size(iconSize)){
        val color = Color.Red
        drawCircle(
            color
        )
    }
}