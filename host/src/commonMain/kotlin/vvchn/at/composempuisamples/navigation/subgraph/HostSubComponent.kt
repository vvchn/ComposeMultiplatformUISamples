package vvchn.at.composempuisamples.navigation.subgraph

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import vvchn.at.composempuisamples.navigation.root.HostComponent.Direction

interface HostSubComponent {
    val routerState: Value<ChildStack<*, Direction>>

    sealed class HostChild {
        // TODO: Screens
        data class MainChild(val component: Nothing): HostChild()
        data class AboutChild(val component: Nothing): HostChild()
        data class TodoChild(val component: Nothing): HostChild()
    }
}