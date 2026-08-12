package br.com.mymovielist.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import mymovieslist.theme.generated.resources.Res
import mymovieslist.theme.generated.resources.montserrat_medium
import mymovieslist.theme.generated.resources.montserrat_regular
import mymovieslist.theme.generated.resources.montserrat_semibold
import org.jetbrains.compose.resources.Font

@Composable
private fun montserratFontFamily(): FontFamily = FontFamily(
    listOf(
        Font(Res.font.montserrat_regular),
        Font(Res.font.montserrat_medium, FontWeight.Medium),
        Font(Res.font.montserrat_semibold, FontWeight.SemiBold)
    )
)

@Composable
private fun defaultTextStyle(): TextStyle = TextStyle(
    fontFamily = montserratFontFamily(),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

@Suppress("LongMethod")
@Composable
fun myMoviesTypography(): Typography {
    val defaultTextStyle = defaultTextStyle()
    return Typography(
        displayLarge = defaultTextStyle.copy(
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        ),
        displayMedium = defaultTextStyle.copy(
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = defaultTextStyle.copy(
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),
        headlineLarge = defaultTextStyle.copy(
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = defaultTextStyle.copy(
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = defaultTextStyle.copy(
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        titleLarge = defaultTextStyle.copy(
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = defaultTextStyle.copy(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            fontWeight = FontWeight.Medium
        ),
        titleSmall = defaultTextStyle.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            fontWeight = FontWeight.Medium
        ),
        labelLarge = defaultTextStyle.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            fontWeight = FontWeight.Medium
        ),
        labelMedium = defaultTextStyle.copy(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.Medium
        ),
        labelSmall = defaultTextStyle.copy(
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.Medium
        ),
        bodyLarge = defaultTextStyle.copy(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = defaultTextStyle.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = defaultTextStyle.copy(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
    )
}
