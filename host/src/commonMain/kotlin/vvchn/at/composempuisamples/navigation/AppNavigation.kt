package vvchn.at.composempuisamples.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import vvchn.at.composempuisamples.navigation.root.HostComponent

@Composable
fun AppNavigation(appNavController: HostComponent) {
    val routerState by appNavController.routerState.subscribeAsState()

    // TODO: ThemeProvider
}