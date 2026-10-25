package com.electrohub.fixly.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val FixlyColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    onPrimary = FixlyWhite,
    primaryContainer = PrimaryGreen,
    onPrimaryContainer = FixlyWhite,
    secondary = DarkTeal,
    onSecondary = FixlyWhite,
    secondaryContainer = SurfaceVariant,
    onSecondaryContainer = PrimaryText,
    tertiary = DarkTeal,
    onTertiary = FixlyWhite,
    background = MainBackground,
    onBackground = PrimaryText,
    surface = CardBackground,
    onSurface = PrimaryText,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = SecondaryText,
    outline = BorderColor,
    error = ErrorRed,
    onError = FixlyWhite
)

@Composable
fun FixlyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FixlyColorScheme,
        typography = Typography,
        content = content
    )
}
