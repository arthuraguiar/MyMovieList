package com.example.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MyMoviesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorPalette,
        typography = Typography,
        content = content
    )
}