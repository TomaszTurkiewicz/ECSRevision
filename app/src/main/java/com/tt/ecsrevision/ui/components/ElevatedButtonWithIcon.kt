package com.tt.ecsrevision.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tt.ecsrevision.ui.shapes.AnswerListShape
import com.tt.ecsrevision.ui.shapes.InfoIconShape
import com.tt.ecsrevision.ui.shapes.ResetShape
import com.tt.ecsrevision.ui.theme.myColors

const val RESET_ICON = 1
const val ANSWER_SETTINGS_ICON = 2
const val INFO_ICON = 3

@Composable
fun ElevatedButtonWithIcon(
    modifier: Modifier,
    iconType:Int,
    oneAnswer:Boolean,
    corners: Dp = 20.dp,
    onClick: () -> Unit
) {

    var heightDp by remember {
        mutableStateOf(0.dp)
    }

    val localDensity = LocalDensity.current

    ElevatedButton(
        onClick = {
            onClick()
        },
        modifier = modifier
            .aspectRatio(1f)
            .onGloballyPositioned { coordinates ->
                heightDp = with(localDensity) {
                    coordinates.size.height.toDp()
                }
            },
        contentPadding = PaddingValues(
            start = 0.dp,
            end = 0.dp,
            top = 0.dp,
            bottom = 0.dp
        ),
        shape = RoundedCornerShape(corners),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.myColors.primary,
            contentColor = Color.Black,
            disabledContentColor = MaterialTheme.myColors.primary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 1.dp,
            disabledElevation = 0.dp
        )
    ) {

        Box(
            modifier =Modifier.fillMaxSize()
        ) {

            when (iconType) {
                RESET_ICON -> ResetShape(iconSize = heightDp)
                ANSWER_SETTINGS_ICON -> AnswerListShape(iconSize = heightDp, oneAnswer = oneAnswer)
                INFO_ICON -> InfoIconShape(iconSize = heightDp)
            }
        }

    }
}