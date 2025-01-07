package vvchn.at.composempuisamples.navigation.subgraph

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import vvchn.at.composempuisamples.navigation.root.HostComponent

class HostSubComponentImpl(
    componentContext: ComponentContext,
) : HostSubComponent, ComponentContext by componentContext {
    private val hostNavigation = StackNavigation<HostSubConfig>()

    override val routerState: Value<ChildStack<*, HostComponent.Direction>> =
        childStack(
            source = hostNavigation,
            serializer = HostSubConfig.serializer(),
            initialStack = { listOf(HostSubConfig.Main) },
            handleBackButton = true,
            childFactory = ::hostChild
        )

    private fun hostChild(
        config: HostSubConfig,
        componentContext: ComponentContext
    ): HostComponent.Direction =
        when (config) {
            // TODO: Screens
            HostSubConfig.About -> TODO()
            HostSubConfig.Main -> TODO()
            HostSubConfig.Todo -> TODO()
        }

    @Serializable
    sealed class HostSubConfig {
        @Serializable
        data object Main : HostSubConfig()
        data object About : HostSubConfig()
        data object Todo : HostSubConfig()
    }
}