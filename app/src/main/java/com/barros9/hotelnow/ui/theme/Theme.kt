package com.barros9.hotelnow.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Blue600,
    primaryVariant = Blue800,
    secondary = Orange800,
    secondaryVariant = Orange800
)

private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue800,
    secondary = Orange300,
    secondaryVariant = Orange300
)

@Composable
fun HotelNowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}