package vvchn.at.composempuisamples

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import vvchn.at.composempuisamples.navigation.root.HostComponent
import vvchn.at.composempuisamples.navigation.root.HostComponentImpl
import vvchn.at.composempuisamples.utils.runOnUiThread

fun main() {
    val lifecycle = LifecycleRegistry()

    val appNavController: HostComponent = runOnUiThread {
        HostComponentImpl(componentContext = DefaultComponentContext(lifecycle = lifecycle))
    }
    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Compose Multiplatform UI Samples",
        ) {
            App(appNavController)
        }
    }
}