package vvchn.at.composempuisamples.navigation.host

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import vvchn.at.composempuisamples.screens.main.MainScreenComponent
import vvchn.at.composempuisamples.screens.todo.TodoScreenComponent

interface HostComponent {
    fun pop()

    val controllerState: Value<ChildStack<*, HostChild>>

    sealed class HostChild {
        // TODO: Screens
        data class AboutChild(val component: Nothing): HostChild()
        data class MainChild(val component: MainScreenComponent): HostChild()
        data class TodoChild(val component: TodoScreenComponent): HostChild()
    }
}