package vvchn.at.composempuisamples.navigation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import vvchn.at.composempuisamples.navigation.subgraph.HostSubComponent

interface HostComponent {
    val routerState: Value<ChildStack<*, Direction>>

    sealed class Direction {
        // TODO: Directions
        data class HostRoot(val hostSubComponent: HostSubComponent) : Direction()
    }
}