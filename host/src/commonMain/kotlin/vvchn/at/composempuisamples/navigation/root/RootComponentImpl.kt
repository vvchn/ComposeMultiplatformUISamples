package vvchn.at.composempuisamples.navigation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import vvchn.at.composempuisamples.navigation.host.HostComponentImpl

internal class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    private val rootNavigation = StackNavigation<RootConfig>()

    override val controllerState: Value<ChildStack<*, RootComponent.Direction>> =
        childStack(
            source = rootNavigation,
            serializer = RootConfig.serializer(),
            initialStack = { listOf(RootConfig.Host) },
            handleBackButton = true,
            childFactory = ::direction
        )

    private fun direction(
        config: RootConfig,
        componentContext: ComponentContext
    ): RootComponent.Direction =
        when (config) {
            RootConfig.Host -> RootComponent.Direction.HostRoot(
                HostComponentImpl(
                    componentContext
                )
            )
        }

    @Serializable
    sealed class RootConfig {
        @Serializable
        data object Host : RootConfig()
    }
}