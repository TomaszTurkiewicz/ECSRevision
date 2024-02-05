package com.tt.ecsrevision.ui.screens

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tt.ecsrevision.R
import com.tt.ecsrevision.ui.components.CustomButtonWithText
import com.tt.ecsrevision.viewmodels.AppViewModel

@Composable
fun ChooserScreen(
    moveToRevision: () -> Unit,
    moveToTest: () -> Unit,
    moveToOtherApps: () -> Unit
)
{
    val buttonAspectRatio = 3f
    val widthRatio = 0.5f
    val context = LocalContext.current

    val activity = LocalContext.current as Activity
    BackHandler {
        activity.finish()
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
       Box (
           modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight(0.33f),
           contentAlignment = Alignment.Center
       ){
           CustomButtonWithText(
               title = context.getString(R.string.revision),
               modifier = Modifier
                   .fillMaxWidth(widthRatio)
                   .aspectRatio(buttonAspectRatio))
           {
            moveToRevision()
           }
       }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center
        ){
            CustomButtonWithText(
                title = context.getString(R.string.test),
                modifier = Modifier
                    .fillMaxWidth(widthRatio)
                    .aspectRatio(buttonAspectRatio))
            {
              moveToTest()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            CustomButtonWithText(
                title = context.getString(R.string.other_apps),
                modifier = Modifier
                    .fillMaxWidth(widthRatio)
                    .aspectRatio(buttonAspectRatio))
            {
                moveToOtherApps()
            }
        }
    }
}

//@Preview
//@Composable
//fun ChooserScreenPreview(){
//    ChooserScreen {//do nothing
//    }
//}
