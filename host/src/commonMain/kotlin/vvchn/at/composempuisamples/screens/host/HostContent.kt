package vvchn.at.composempuisamples.screens.host

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import vvchn.at.composempuisamples.screens.main.MainScreenRoot
import vvchn.at.composempuisamples.screens.todo.TodoScreenRoot
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar

@Composable
fun HostContent(hostComponent: HostComponent) {
    val routerState by hostComponent.controllerState.subscribeAsState()

    Scaffold(
        topBar = {
            HostTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = HostTheme.hostDimens.headerHeight)
                    .statusBarsPadding(),
                isBtnVisible = (routerState.active.instance !is HostChild.MainChild),
                onBtnClicked = { hostComponent.actionHandler(
                    HostContentAction.MoveBackByPressingTopBarBtn
                ) }
            )
        }
    ) { paddingValues ->
        Children(
            modifier = Modifier.padding(paddingValues),
            stack = routerState
        ) { child ->
            when (val instance = child.instance) {
                is HostChild.AboutChild -> TODO()
                is HostChild.MainChild -> MainScreenRoot(instance.component)
                is HostChild.TodoChild -> TodoScreenRoot(instance.component)
            }
        }
    }
}