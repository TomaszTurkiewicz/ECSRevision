package com.tt.ecsrevision.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.ui.theme.myColors
import java.nio.file.WatchEvent
import java.util.concurrent.CancellationException

@Composable
fun CustomButtonWithText(
    title:String,
    modifier: Modifier,
    onClick: () -> Unit
) {

    val touchedDown = remember {
        mutableStateOf(false)
    }

    var backgroundColor = MaterialTheme.myColors.secondary
    val textColor = MaterialTheme.myColors.background

    if(touchedDown.value){
        backgroundColor = MaterialTheme.myColors.tertiary
    }

    Card (
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        touchedDown.value = true
                        //start
                        val released = try {
                            tryAwaitRelease()
                        } catch (c: CancellationException) {
                            false
                        }
                        if (released) {
                            //ACTION_UP
                            touchedDown.value = false
                            onClick()
                        } else {
                            //CANCELLED
                            touchedDown.value = false
                        }

                    },
                )
            }
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true,
                ambientColor = MaterialTheme.myColors.primary,
                spotColor = MaterialTheme.myColors.primary
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    )
    {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            ComposeAutoResizedText(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = textColor
                ))
        }
    }
}