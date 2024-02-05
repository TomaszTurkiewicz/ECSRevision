package com.tt.ecsrevision.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tt.ecsrevision.viewmodels.AppViewModel

@Composable
fun TestScreen(
    viewModel:AppViewModel,
    intro:Boolean
) {
    if(intro){
        //when displaying intro, loading rewarded ad and preparing list of questions
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        )
    }else{
        // navigating through test (display time!!!)
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Red))
    }

}