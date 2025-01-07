package vvchn.at.composempuisamples

import androidx.compose.ui.window.ComposeUIViewController
import vvchn.at.composempuisamples.navigation.root.HostComponent

fun MainViewController(appNavController: HostComponent) = ComposeUIViewController { App(appNavController) }