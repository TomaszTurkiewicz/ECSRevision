package com.tt.ecsrevision.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WelcomeScreen(
    onClick: () -> Unit
)
{
    Text(
        text = "LOADING",
        modifier = Modifier.clickable(
            onClick = onClick
        )
    )
}