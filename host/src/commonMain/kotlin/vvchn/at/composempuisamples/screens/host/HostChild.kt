package vvchn.at.composempuisamples.screens.host

import vvchn.at.composempuisamples.screens.main.MainScreenComponent
import vvchn.at.composempuisamples.screens.todo.TodoScreenComponent

sealed class HostChild {
    // TODO: Screens
    data class AboutChild(val component: Nothing): HostChild()
    data class MainChild(val component: MainScreenComponent): HostChild()
    data class TodoChild(val component: TodoScreenComponent): HostChild()
}