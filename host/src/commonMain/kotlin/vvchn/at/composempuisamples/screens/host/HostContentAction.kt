package vvchn.at.composempuisamples.screens.host

import vvchn.at.composempuisamples.mvi.Action

sealed interface HostContentAction : Action {
    data object MoveBackByPressingTopBarBtn : HostContentAction
}