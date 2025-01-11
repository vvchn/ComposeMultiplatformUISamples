package vvchn.at.composempuisamples

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import vvchn.at.composempuisamples.navigation.AppNavigation
import vvchn.at.composempuisamples.navigation.RootComponent

@Composable
@Preview
fun App(appNavComponent: RootComponent) {
    AppNavigation(appNavComponent)
}