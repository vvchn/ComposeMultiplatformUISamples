package vvchn.at.composempuisamples.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
actual fun isDarkThemeEnabled(): Boolean = isSystemInDarkTheme()