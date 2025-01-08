package vvchn.at.composempuisamples.navigation.host

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import vvchn.at.composempuisamples.screens.main.MainScreenComponent

interface HostComponent {
    val controllerState: Value<ChildStack<*, HostChild>>

    sealed class HostChild {
        // TODO: Screens
        data class MainChild(val component: MainScreenComponent): HostChild()
        data class AboutChild(val component: Nothing): HostChild()
        data class TodoChild(val component: Nothing): HostChild()
    }
}