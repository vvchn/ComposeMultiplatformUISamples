package vvchn.at.composempuisamples.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import org.jetbrains.skiko.SystemTheme.DARK
import org.jetbrains.skiko.currentSystemTheme

@Composable
actual fun isDarkThemeEnabled(): Boolean {
    var isDark by remember { mutableStateOf(currentSystemTheme == DARK) }

    LaunchedEffect(Unit) {
        while (true) {
            val newValue = currentSystemTheme == DARK
            if (isDark != newValue) {
                isDark = newValue
            }
            delay(1000)
        }
    }

    return isDark
}
