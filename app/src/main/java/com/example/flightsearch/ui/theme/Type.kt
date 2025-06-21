package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.flightsearch.R

val poppinsRegular = FontFamily(
    Font(R.font.regular)
)

val poppinsMedium = FontFamily(
    Font(R.font.medium)
)

val poppinsSemiBold = FontFamily(
    Font(R.font.semi_bold)
)

val poppinsBold = FontFamily(
    Font(R.font.bold)
)

val typography = Typography(
    titleMedium = TextStyle(
        fontFamily = poppinsSemiBold,
        fontSize = 24.sp,
        letterSpacing = 3.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = poppinsSemiBold,
        fontSize = 20.sp,
        letterSpacing = 1.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = poppinsSemiBold,
        fontSize = 16.sp,
        letterSpacing = 1.sp
    ),
    bodySmall = TextStyle(
        fontFamily = poppinsRegular,
        fontSize = 14.sp,
        letterSpacing = 1.sp
    ),
    labelLarge = TextStyle(
        fontFamily = poppinsSemiBold,
        fontSize = 14.sp,
        letterSpacing = 1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = poppinsRegular,
        fontSize = 14.sp,
        letterSpacing = 1.sp
    )
)