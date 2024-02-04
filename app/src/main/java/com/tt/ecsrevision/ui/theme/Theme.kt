package com.tt.ecsrevision.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

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
    material = darkColorScheme(),
    correctAnswerInner = DarkGreenInner,
    correctAnswerBorder = DarkGreenBorder,
)

private val LightMyColorPalette = CustomColorsPalette(
    material = lightColorScheme(),
    correctAnswerInner = LightGreenInner,
    correctAnswerBorder = LightGreenBorder
)

val LocalColors = staticCompositionLocalOf { LightMyColorPalette }
@Composable
fun ECSRevisionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {

        darkTheme -> DarkMyColorPalette
        else -> LightMyColorPalette
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
            colorScheme = colors.material,
            content = content
        )
    }
}