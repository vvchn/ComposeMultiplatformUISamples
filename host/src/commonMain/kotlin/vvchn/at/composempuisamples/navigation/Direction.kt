package vvchn.at.composempuisamples.navigation

import vvchn.at.composempuisamples.screens.host.HostComponent

sealed class Direction {
    data class HostRoot(val hostComponent: HostComponent) : Direction()
}