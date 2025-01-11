package vvchn.at.composempuisamples.screens.host

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import vvchn.at.composempuisamples.screens.main.MainScreenRoot
import vvchn.at.composempuisamples.screens.todo.TodoScreenRoot
import vvchn.at.composempuisamples.widgets.HostTopBar

@Composable
fun HostContent(hostComponent: HostComponent) {
    val routerState by hostComponent.controllerState.subscribeAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Scaffold(
            topBar = {
                HostTopBar(
                    isBtnVisible = (routerState.active.instance !is HostChild.MainChild),
                    onBtnClicked = { hostComponent.actionHandler(HostContentAction.MoveBackByPressingTopBarBtn) }
                )
            }
        ) {
            Children(stack = routerState) { child ->
                when (val instance = child.instance) {
                    is HostChild.AboutChild -> TODO()
                    is HostChild.MainChild -> MainScreenRoot(instance.component)
                    is HostChild.TodoChild -> TodoScreenRoot(instance.component)
                }
            }
        }
    }
}