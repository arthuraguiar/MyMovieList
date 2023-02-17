package com.example.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = md_theme_dark_primary,
    primaryVariant = Color(0xFF3700B3),
    secondary = md_theme_dark_secondary,
    secondaryVariant = md_theme_dark_secondary,
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
    error = Color(0xFFCF6679),
    onPrimary = Color(0xFFCF6679),
    onSecondary = md_theme_dark_onSecondary,
    onBackground = md_theme_dark_onBackground,
    onSurface = md_theme_dark_onSurface,
    onError = Color.Black
)

@Composable
fun MyMoviesTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        content = content
    )
}