package vvchn.at.composempuisamples.screens.main

import vvchn.at.composempuisamples.mvi.Action

sealed interface MainScreenAction : Action {
    data object OpenAboutPage : MainScreenAction
    data object OpenSample1 : MainScreenAction
    data object OpenSample2 : MainScreenAction
}