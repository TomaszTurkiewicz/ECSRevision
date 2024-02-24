package com.tt.ecsrevision.ui.alertdialogs

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.R
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.ui.components.ElevatedButtonWithText
import com.tt.ecsrevision.ui.theme.myColors

@Composable
fun NotAllAnsweredAlertDialog(
    context: Context,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onNegativeClick()
        },
        title = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)){
                ComposeAutoResizedText(
                    text = context.getString(R.string.warning),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.myColors.textRed
                    ),
                    modifier = Modifier
                        .align(Alignment.Center))
            }
        },
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center)
            {
                ComposeAutoResizedText(
                    text = context.getString(R.string.not_all_answered),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge)
            }
        },
        buttons = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ){
                Box (
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
//                        .padding(
//                            start = 20.dp,
//                            top = 20.dp,
//                            bottom = 20.dp
//                        ),
                    contentAlignment = Alignment.Center
                ){
                    ElevatedButtonWithText(
                        text = context.getString(R.string.dismiss),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)) {
                        onNegativeClick()
                    }
                }

                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
//                        .padding(
//                            end = 20.dp,
//                            top = 20.dp,
//                            bottom = 20.dp
//                        ),
                    contentAlignment = Alignment.Center
                ){
                    ElevatedButtonWithText(
                        text = context.getString(R.string.finish),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)) {
                        onPositiveClick()
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.myColors.primaryContainer,
        shape = RoundedCornerShape(30.dp)
    )
}