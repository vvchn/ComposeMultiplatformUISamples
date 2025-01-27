package vvchn.at.composempuisamples.screens.about

import vvchn.at.composempuisamples.mvi.Action

sealed interface AboutScreenAction : Action {
    data object ShowLicense: AboutScreenAction
}