package vvchn.at.composempuisamples

import androidx.compose.ui.window.ComposeUIViewController
import vvchn.at.composempuisamples.navigation.RootComponent

fun MainViewController(appNavComponent: RootComponent) = ComposeUIViewController { App(appNavComponent) }