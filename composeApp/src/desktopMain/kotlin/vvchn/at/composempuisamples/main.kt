package vvchn.at.composempuisamples

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import vvchn.at.composempuisamples.navigation.RootComponent
import vvchn.at.composempuisamples.utils.runOnUiThread

fun main() {
    val appNavComponent = runOnUiThread {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Compose Multiplatform UI Samples",
        ) {
            App(appNavComponent)
        }
    }
}