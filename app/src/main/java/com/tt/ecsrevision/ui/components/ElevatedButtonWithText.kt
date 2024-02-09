package com.tt.ecsrevision.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.ui.theme.myColors

@Composable
fun ElevatedButtonWithText(
    text:String,
    modifier: Modifier,
    enable:Boolean = true,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = {
            onClick()
                  },
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        enabled = enable,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.myColors.primary,
            contentColor = Color.Black,
            disabledContentColor = MaterialTheme.myColors.primary,
            disabledContainerColor = MaterialTheme.myColors.buttonNotActive
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 1.dp,
            disabledElevation = 0.dp
        )
    ) {
        ComposeAutoResizedText(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge)
    }
}