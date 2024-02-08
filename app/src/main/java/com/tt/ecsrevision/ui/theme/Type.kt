package com.tt.ecsrevision.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val TtTypography = Typography(

    /** for
     * any normal text in the app
     * buttons
     * welcome screen - checking database version
     * answers
     * explanations
     * clock
     * etc **/
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),

    /** for important text
     * questions
     * pass mark
     * test time **/
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),

    /** for
     * top bar in the whole app only **/
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        lineHeight = 50.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),

    /** for
     * welcome screen WELCOME text on top of the screen
     * test summary SUMMARY and FAILED/PASS **/
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 50.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    )

    /* Other default text styles to override
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)