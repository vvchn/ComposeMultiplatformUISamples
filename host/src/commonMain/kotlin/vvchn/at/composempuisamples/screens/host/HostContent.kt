package vvchn.at.composempuisamples.screens.host

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import vvchn.at.composempuisamples.screens.about.AboutScreenRoot
import vvchn.at.composempuisamples.screens.main.MainScreenRoot
import vvchn.at.composempuisamples.screens.todo.TodoScreenRoot
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar
import vvchn.at.composempuisamples.widgets.absoluteHorizontalPadding

@Composable
fun HostContent(hostComponent: HostComponent) {
    val routerState by hostComponent.controllerState.subscribeAsState()
    var headerTextWidth by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            HostTopBar(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .height(HostTheme.hostDimens.headerHeight),
                isBtnVisible = (routerState.active.instance !is HostChild.MainChild),
                onTextWidthChanged = { headerTextWidth = it },
                onBtnClicked = {
                    hostComponent.actionHandler(
                        HostContentAction.MoveBackByPressingTopBarBtn
                    )
                }
            )
        }
    ) { paddingValues ->
        Children(
            modifier = Modifier.padding(paddingValues),
            stack = routerState
        ) { child ->
            when (val instance = child.instance) {
                is HostChild.AboutChild -> AboutScreenRoot(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = HostTheme.hostDimens.aboutScreenTopPadding,
                            bottom = HostTheme.hostDimens.aboutScreenBottomPadding
                        )
                        .absoluteHorizontalPadding(headerTextWidth),
                    component = instance.component
                )

                is HostChild.MainChild -> MainScreenRoot(
                    modifier = Modifier.fillMaxSize().absoluteHorizontalPadding(headerTextWidth),
                    component = instance.component
                )

                is HostChild.TodoChild -> TodoScreenRoot(instance.component)
            }
        }
    }
}