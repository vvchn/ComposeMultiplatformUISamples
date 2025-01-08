package vvchn.at.composempuisamples.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import vvchn.at.composempuisamples.common.MaterialTypography

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
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val materialColorScheme =
        if (darkTheme) {
            val darkColors = hostDarkThemeColors
            darkColorScheme(
                primary = darkColors.primary,
                secondary = darkColors.secondary,
                tertiary = darkColors.tertiary
            )
        } else {
            val lightColors = hostLightThemeColors
            lightColorScheme(
                primary = lightColors.primary,
                secondary = lightColors.secondary,
                tertiary = lightColors.tertiary
            )
        }

    val hostColorScheme = if (darkTheme) hostDarkThemeColors else HostTheme.hostColors

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