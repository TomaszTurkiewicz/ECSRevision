package com.tt.ecsrevision.ui.screens.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.tt.ecsrevision.ui.components.ComposeAutoResizedText
import com.tt.ecsrevision.viewmodels.AppViewModel
import java.nio.file.WatchEvent

@Composable
fun TestIntroScreen(
    viewModel: AppViewModel
){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Red)
    ) {
        //title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .background(Color.Yellow),
            contentAlignment = Alignment.Center
        ) {
            ComposeAutoResizedText(
                text = "ECS TEST",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge)
        }

        // test time
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ){
            ComposeAutoResizedText(
                text = "Test time: "+ viewModel.getTestTimeInt() + " minutes",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge)
        }

        // total questions

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ){
            ComposeAutoResizedText(
                text = "Total questions: "+ viewModel.getTestNumberOfQuestions(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.background(Color.Cyan))
        }

        // pass mark

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ){
            ComposeAutoResizedText(
                text = "Pass mark: "+ viewModel.getPassMarkInt(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}