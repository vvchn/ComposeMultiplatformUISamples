package vvchn.at.composempuisamples.navigation.host

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import vvchn.at.composempuisamples.screens.main.MainScreenComponent

internal class HostComponentImpl(
    componentContext: ComponentContext,
) : HostComponent, ComponentContext by componentContext {
    private val hostNavigation = StackNavigation<HostConfig>()

    override val controllerState: Value<ChildStack<*, HostComponent.HostChild>> =
        childStack(
            source = hostNavigation,
            serializer = HostConfig.serializer(),
            initialStack = { listOf(HostConfig.Main) },
            handleBackButton = true,
            childFactory = ::hostChild
        )

    private fun hostChild(
        config: HostConfig,
        componentContext: ComponentContext
    ): HostComponent.HostChild =
        when (config) {
            // TODO: Screens
            HostConfig.About -> TODO()
            HostConfig.Main -> HostComponent.HostChild.MainChild(MainScreenComponent(componentContext))
            HostConfig.Todo -> TODO()
        }

    @Serializable
    sealed class HostConfig {
        @Serializable
        data object Main : HostConfig()
        data object About : HostConfig()
        data object Todo : HostConfig()
    }
}