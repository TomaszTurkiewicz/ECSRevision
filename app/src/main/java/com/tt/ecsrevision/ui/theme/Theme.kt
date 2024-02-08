package com.tt.ecsrevision.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//)

private val DarkMyColorPalette = CustomColorsPalette(
    material = darkColorScheme(
        primary = Orange500,
        primaryContainer = Orange100
    ),
    correctAnswerInner = DarkGreenInner,
    correctAnswerBorder = DarkGreenBorder,
    buttonNotActive = LightGray,
    primaryDark = Orange700,
    primaryLight = Orange200
)

private val LightMyColorPalette = CustomColorsPalette(
    material = lightColorScheme(
        primary = Orange500,
        primaryContainer = Orange100
    ),
    correctAnswerInner = LightGreenInner,
    correctAnswerBorder = LightGreenBorder,
    buttonNotActive = LightGray,
    primaryDark = Orange700,
    primaryLight = Orange200
)

val LocalColors = staticCompositionLocalOf { LightMyColorPalette }

@Composable
fun ECSRevisionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemsUiController = rememberSystemUiController()
    val colors = when {

        darkTheme -> DarkMyColorPalette
        else -> LightMyColorPalette
    }

    if(darkTheme){
        systemsUiController.setStatusBarColor(
            MaterialTheme.myColors.primaryDark
        )
    }else{
        systemsUiController.setStatusBarColor(
            MaterialTheme.myColors.primaryDark
        )
    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
    CompositionLocalProvider(LocalColors provides colors){
        MaterialTheme (
            typography = TtTypography,
            colorScheme = colors.material,
            content = content
        )
    }
}