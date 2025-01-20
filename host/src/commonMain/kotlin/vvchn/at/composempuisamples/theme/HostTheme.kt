package vvchn.at.composempuisamples.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import vvchn.at.composempuisamples.compose.MaterialTypography
import vvchn.at.composempuisamples.compose.isDarkThemeEnabled

private val LocalHostThemeColorScheme = staticCompositionLocalOf { hostLightThemeColors }
private val LocalHostThemeTypography = staticCompositionLocalOf { HostTypography() }
private val LocalHostThemeShapes = staticCompositionLocalOf { HostShapes() }
private val LocalHostThemeDimens = staticCompositionLocalOf { HostDimens() }

@Immutable
internal object HostTheme {
    val hostColors: HostColors
        @Composable
        @ReadOnlyComposable
        get() = LocalHostThemeColorScheme.current

    val hostTypography: HostTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalHostThemeTypography.current

    val hostShapes: HostShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalHostThemeShapes.current

    val hostDimens: HostDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalHostThemeDimens.current
}

@Composable
internal fun HostTheme(
    appliedColorModeIsDark: Boolean? = null,
    content: @Composable () -> Unit
) {
    val materialColorScheme: ColorScheme
    val hostColorScheme: HostColors
    val darkTheme: Boolean

    when (appliedColorModeIsDark) {
        true -> {
            materialColorScheme = hostDarkColorScheme(hostDarkThemeColors)
            hostColorScheme = hostDarkThemeColors
        }

        false -> {
            materialColorScheme = hostLightColorScheme(hostLightThemeColors)
            hostColorScheme = HostTheme.hostColors
        }

        null -> {
            darkTheme = isDarkThemeEnabled()
            materialColorScheme =
                if (darkTheme) {
                    hostColorScheme = hostDarkThemeColors
                    hostDarkColorScheme(hostDarkThemeColors)
                } else {
                    hostColorScheme = HostTheme.hostColors
                    hostLightColorScheme(hostLightThemeColors)
                }
        }
    }

    CompositionLocalProvider(
        LocalHostThemeColorScheme provides hostColorScheme,
        LocalHostThemeTypography provides HostTypography(),
        LocalHostThemeShapes provides HostShapes(),
        LocalHostThemeDimens provides HostDimens()
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = MaterialTypography,
            content = content
        )
    }
}

@Stable
private fun hostLightColorScheme(lightColors: HostColors) = lightColorScheme(
    primary = lightColors.primary,
    secondary = lightColors.secondary,
    tertiary = lightColors.tertiary
)

@Stable
private fun hostDarkColorScheme(darkColors: HostColors) = darkColorScheme(
    primary = darkColors.primary,
    secondary = darkColors.secondary,
    tertiary = darkColors.tertiary
)