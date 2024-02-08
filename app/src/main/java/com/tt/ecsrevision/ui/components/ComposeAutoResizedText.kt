package com.tt.ecsrevision.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.isUnspecified

@Composable
fun ComposeAutoResizedText(
    text:String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    textAlign: TextAlign,
) {

    val currentStyle:TextStyle = style

    var resizedTextStyle by remember(text) {
        mutableStateOf(currentStyle)
    }

    var shouldDraw by remember(text) {
        mutableStateOf(false)
    }

    val defaultFontSize = MaterialTheme.typography.titleLarge.fontSize

    Text(
        text = text,
        modifier = modifier.drawWithContent {
                  if(shouldDraw){
                      drawContent()
                  }
        },
        style = resizedTextStyle,
        textAlign = textAlign,
        onTextLayout = { result ->
            if(result.didOverflowHeight){
                if(style.fontSize.isUnspecified){
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95,
                    lineHeight = resizedTextStyle.lineHeight * 0.95
                )
            }else{
                shouldDraw = true
            }

        }
    )
}
