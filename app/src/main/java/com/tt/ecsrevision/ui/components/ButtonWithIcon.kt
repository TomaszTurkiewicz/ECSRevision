package com.tt.ecsrevision.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.ui.shapes.AnswerListShape
import com.tt.ecsrevision.ui.shapes.InfoIconShape
import com.tt.ecsrevision.ui.shapes.ResetShape
import com.tt.ecsrevision.ui.theme.myColors
import java.util.concurrent.CancellationException

const val RESET_ICON = 1
const val ANSWER_SETTINGS_ICON = 2
const val INFO_ICON = 3

@Composable
fun ButtonWithIcon(
    modifier: Modifier,
    iconType:Int,
    oneAnswer:Boolean,
    corners: Dp = 20.dp,
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
                shape = RoundedCornerShape(corners),
                clip = true,
                ambientColor = MaterialTheme.myColors.primary,
                spotColor = MaterialTheme.myColors.primary
            )
            .onGloballyPositioned { coordinates ->
                heightDp = with(localDensity) {
                    coordinates.size.height.toDp()
                }
            },
        shape = RoundedCornerShape(corners),
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
            when(iconType){
                RESET_ICON -> ResetShape(iconSize = heightDp)
                ANSWER_SETTINGS_ICON -> AnswerListShape(iconSize = heightDp,oneAnswer = oneAnswer)
                INFO_ICON -> InfoIconShape(iconSize = heightDp)
            }

        }
    }
}

