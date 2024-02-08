package com.tt.ecsrevision.ui.theme




import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

@Immutable
data class CustomColorsPalette(
    val material: ColorScheme,
    val correctAnswerInner: Color,
    val correctAnswerBorder: Color,
    val wrongAnswerInner: Color,
    val wrongAnswerBorder: Color,
    val buttonNotActive: Color,
    val primaryDark:Color,
    val primaryLight:Color,
    val text:Color,
    val textGreen:Color,
    val textRed:Color
){
    val primary:Color get() = material.primary
    val onPrimary:Color get() = material.onPrimary
    val primaryContainer:Color get() = material.primaryContainer
    val onPrimaryContainer:Color get() = material.onPrimaryContainer
    val inversePrimary:Color get() = material.inversePrimary
    val secondary:Color get() = material.secondary
    val onSecondary:Color get() = material.onSecondary
    val secondaryContainer:Color get() = material.secondaryContainer
    val onSecondaryContainer:Color get() = material.onSecondaryContainer
    val tertiary:Color get() = material.tertiary
    val onTertiary:Color get() = material.onTertiary
    val tertiaryContainer:Color get() = material.tertiaryContainer
    val onTertiaryContainer:Color get() = material.onTertiaryContainer
    val background:Color get() = material.background
    val onBackground:Color get() = material.onBackground
    val surface:Color get() = material.surface
    val onSurface:Color get() = material.onSurface
    val surfaceVariant:Color get() = material.surfaceVariant
    val onSurfaceVariant:Color get() = material.onSurfaceVariant
    val surfaceTint:Color get() = material.surfaceTint
    val inverseSurface:Color get() = material.inverseSurface
    val inverseOnSurface:Color get() = material.inverseOnSurface
    val error:Color get() = material.error
    val onError:Color get() = material.onError
    val errorContainer:Color get() = material.errorContainer
    val onErrorContainer:Color get() = material.onErrorContainer
    val outline:Color get() = material.outline
    val outlineVariant:Color get() = material.outlineVariant
    val scrim:Color get() = material.scrim

}
