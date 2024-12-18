package vvchn.at.composempuisamples

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose Multiplatform UI Samples",
    ) {
        App()
    }
}