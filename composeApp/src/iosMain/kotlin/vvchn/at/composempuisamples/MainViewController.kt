package vvchn.at.composempuisamples

import androidx.compose.ui.window.ComposeUIViewController
import vvchn.at.composempuisamples.navigation.root.RootComponent

fun MainViewController(appNavController: RootComponent) = ComposeUIViewController { App(appNavController) }