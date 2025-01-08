package vvchn.at.composempuisamples.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import vvchn.at.composempuisamples.screens.main.MainScreen

@Composable
fun HostContent(hostComponent: HostComponent) {
    val routerState by hostComponent.controllerState.subscribeAsState()

    Children(stack = routerState) { child ->
        when (val instance = child.instance) {
            is HostComponent.HostChild.AboutChild -> TODO()
            is HostComponent.HostChild.MainChild -> MainScreen(instance.component)
            is HostComponent.HostChild.TodoChild -> TODO()
        }
    }
}