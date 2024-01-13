package com.tt.ecsrevision.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tt.ecsrevision.R

@Composable
fun ChooserScreen()
{
    Column {
        Button(
            onClick = { /*TODO*/ })
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