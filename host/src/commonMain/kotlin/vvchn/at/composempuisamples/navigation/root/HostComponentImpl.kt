package vvchn.at.composempuisamples.navigation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import vvchn.at.composempuisamples.navigation.subgraph.HostSubComponentImpl

class HostComponentImpl(
    componentContext: ComponentContext
) : HostComponent, ComponentContext by componentContext {
    private val hostNavigation = StackNavigation<HostConfig>()

    override val routerState: Value<ChildStack<*, HostComponent.Direction>> =
        childStack(
            source = hostNavigation,
            serializer = HostConfig.serializer(),
            initialStack = { listOf(HostConfig.Host) },
            handleBackButton = true,
            childFactory = ::direction
        )

    private fun direction(
        config: HostConfig,
        componentContext: ComponentContext
    ): HostComponent.Direction =
        when (config) {
            HostConfig.Host -> HostComponent.Direction.HostRoot(
                HostSubComponentImpl(
                    componentContext
                )
            )
        }

    @Serializable
    sealed class HostConfig {
        @Serializable
        data object Host : HostConfig()
    }
}