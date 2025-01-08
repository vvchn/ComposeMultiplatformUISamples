package vvchn.at.composempuisamples.navigation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import vvchn.at.composempuisamples.navigation.host.HostComponent

interface RootComponent {
    val controllerState: Value<ChildStack<*, Direction>>

    sealed class Direction {
        data class HostRoot(val hostComponent: HostComponent) : Direction()
    }
}