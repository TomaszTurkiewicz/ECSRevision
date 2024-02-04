package com.tt.ecsrevision.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.ui.theme.myColors
import java.util.concurrent.CancellationException

@Composable
fun ButtonWithIcon(
    modifier: Modifier,
    onClick: () -> Unit
) {
    val touchedDown = remember {
        mutableStateOf(false)
    }

    var backgroundColor = MaterialTheme.myColors.secondary

    if(touchedDown.value){
        backgroundColor = MaterialTheme.myColors.tertiary
    }

    val localDensity = LocalDensity.current

    var heightDp by remember {
        mutableStateOf(0.dp)
    }

    Card (
        modifier = modifier
            .aspectRatio(1f)
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
            )
            .onGloballyPositioned { coordinates ->
                                 heightDp = with(localDensity){
                                     coordinates.size.height.toDp()
                                 }
            },
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
           ResetShape(iconSize = heightDp)
        }
    }
}

