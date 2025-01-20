package vvchn.at.composempuisamples

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.formdev.flatlaf.FlatLightLaf
import com.formdev.flatlaf.util.SystemInfo
import vvchn.at.composempuisamples.compose.isDarkThemeEnabled
import vvchn.at.composempuisamples.navigation.RootComponent
import vvchn.at.composempuisamples.utils.WindowTopBarColors
import vvchn.at.composempuisamples.utils.runOnUiThread
import java.awt.Color
import javax.swing.JFrame

fun main() {
    val appNavComponent = runOnUiThread {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }

    // MacOS has its own mechanism for coloring the top window bar according to the current system color mode
    val runningOnMacOS = SystemInfo.isMacOS

    application {
        val windowState = rememberWindowState(position = WindowPosition.Aligned(Alignment.Center))
        val darkTheme = isDarkThemeEnabled()

        if (runningOnMacOS.not()) {
            FlatLightLaf.setup()
            JFrame.setDefaultLookAndFeelDecorated(true)
        }

        Window(
            state = windowState,
            onCloseRequest = ::exitApplication,
            title = "Compose Multiplatform UI Samples"
        ) {
            if (runningOnMacOS.not()) {
                if (darkTheme) {
                    window.rootPane.putClientProperty("JRootPane.titleBarBackground", WindowTopBarColors.surfaceBlack)
                    window.rootPane.putClientProperty("JRootPane.titleBarForeground", Color.WHITE)
                }
                else {
                    window.rootPane.putClientProperty("JRootPane.titleBarBackground", WindowTopBarColors.surfaceWhite)
                    window.rootPane.putClientProperty("JRootPane.titleBarForeground", Color.BLACK)
                }
            }
            App(appNavComponent)
        }
    }
}
