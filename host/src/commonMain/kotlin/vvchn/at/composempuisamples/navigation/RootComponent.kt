package vvchn.at.composempuisamples.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import vvchn.at.composempuisamples.mvi.Action
import vvchn.at.composempuisamples.mvi.NavComponent
import vvchn.at.composempuisamples.screens.host.HostComponent

class RootComponent(
    componentContext: ComponentContext
) : NavComponent<Action, Direction>(componentContext) {
    private val rootNavigation = StackNavigation<RootConfig>()

    override val controllerState: Value<ChildStack<*, Direction>> =
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
    ): Direction =
        when (config) {
            RootConfig.Host -> Direction.HostRoot(
                HostComponent(
                    componentContext
                )
            )
        }

    @Serializable
    private sealed class RootConfig {
        @Serializable
        data object Host : RootConfig()
    }
}