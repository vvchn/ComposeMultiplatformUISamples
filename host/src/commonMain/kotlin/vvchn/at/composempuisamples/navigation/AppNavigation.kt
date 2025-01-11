package vvchn.at.composempuisamples.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import vvchn.at.composempuisamples.screens.host.HostContent
import vvchn.at.composempuisamples.theme.HostTheme

@Composable
fun AppNavigation(appNavController: RootComponent) {
    val routerState by appNavController.controllerState.subscribeAsState()

    when(val direction = routerState.active.instance) {
        is Direction.HostRoot -> HostTheme { HostContent(direction.hostComponent) }
    }
}