package com.tt.ecsrevision.ui.screens

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tt.ecsrevision.R
import com.tt.ecsrevision.viewmodels.AppViewModel

@Composable
fun ChooserScreen(
    moveToRevision: () -> Unit
)
{

    val activity = LocalContext.current as Activity
    BackHandler {
        activity.finish()
    }

    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                moveToRevision()
            })
        {
            Text(stringResource(R.string.revision))
        }
        Button(
            onClick = { /*TODO*/ })
        {
            Text(stringResource(R.string.test))
        }
    }
}

//@Preview
//@Composable
//fun ChooserScreenPreview(){
//    ChooserScreen {//do nothing
//    }
//}
