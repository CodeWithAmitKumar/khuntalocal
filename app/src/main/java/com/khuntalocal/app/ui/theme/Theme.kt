package com.khuntalocal.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = BrandGreen,
    onPrimary = Color.White,
    primaryContainer = BrandGreenLight,
    onPrimaryContainer = BrandGreenDark,
    secondary = BrandNavy,
    onSecondary = Color.White,
    tertiary = BrandOrange,
    onTertiary = Color.White,
    background = SurfaceLight,
    onBackground = TextPrimaryLight,
    surface = SurfaceCardLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = SurfaceLight,
    onSurfaceVariant = TextSecondaryLight,
    outline = OutlineLight,
    outlineVariant = OutlineLight,
    error = RejectedRed,
    onError = Color.White,
)

private val DarkColors = darkColorScheme(
    primary = BrandGreen,
    onPrimary = Color.White,
    primaryContainer = BrandGreenDark,
    onPrimaryContainer = BrandGreenLight,
    secondary = TextPrimaryDark,
    onSecondary = SurfaceDark,
    tertiary = BrandOrange,
    onTertiary = Color.Black,
    background = SurfaceDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceCardDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceCardDark,
    onSurfaceVariant = TextSecondaryDark,
    outline = OutlineDark,
    outlineVariant = OutlineDark,
    error = RejectedRed,
    onError = Color.White,
)

@Composable
fun KhuntaLocalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = KhuntaTypography,
        shapes = KhuntaShapes,
        content = content,
    )
}
