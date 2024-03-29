package com.tt.ecsrevision.ui.alertdialogs

import android.content.Context
import androidx.compose.foundation.layout.Box
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
fun UseOfflineAlertDialog(
    context: Context,
    onButtonClick: () -> Unit
){
    AlertDialog(
    onDismissRequest ={
        //do nothing
    },
    title = {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
        ){
            ComposeAutoResizedText(
                text = context.getString(R.string.warning),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.Center))
        }
    },
    text = {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            ComposeAutoResizedText(
                text = context.getString(R.string.database_offline),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge)
        }
    },
    buttons = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
//                .padding(20.dp),
            contentAlignment = Alignment.Center
        ){
            ElevatedButtonWithText(
                text = context.getString(R.string.offline),
                modifier = Modifier
                    .fillMaxWidth(0.8f)) {
                onButtonClick()
            }
        }
    },
    backgroundColor = MaterialTheme.myColors.primaryContainer,
    shape = RoundedCornerShape(30.dp)
    )
}