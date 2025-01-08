package vvchn.at.composempuisamples.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Immutable
internal data class HostColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val todo: Color,
)

@Immutable
private object DarkPalette {
    val purple80 = Color(0xFFD0BCFF)
    val purpleGrey80 = Color(0xFFCCC2DC)
    val pink80 = Color(0xFFEFB8C8)
    val acidGreen = Color(0xFFA8C023)
}

@Immutable
private object LightPalette {
    val purple40 = Color(0xFF6650a4)
    val purpleGrey40 = Color(0xFF625b71)
    val pink40 = Color(0xFF7D5260)
}

@Stable
internal val hostLightThemeColors = HostColors(
    primary = LightPalette.purple40,
    secondary = LightPalette.purpleGrey40,
    tertiary = LightPalette.pink40,
    todo = Color.Green,
)

@Stable
internal val hostDarkThemeColors = HostColors(
    primary = DarkPalette.purple80,
    secondary = DarkPalette.purpleGrey80,
    tertiary = DarkPalette.pink80,
    todo = DarkPalette.acidGreen,
)