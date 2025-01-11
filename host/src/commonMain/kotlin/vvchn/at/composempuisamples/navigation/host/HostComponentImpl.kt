package vvchn.at.composempuisamples.navigation.host

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import vvchn.at.composempuisamples.screens.main.MainScreenComponent
import vvchn.at.composempuisamples.screens.todo.TodoScreenComponent

internal class HostComponentImpl(
    componentContext: ComponentContext,
) : HostComponent, ComponentContext by componentContext {
    private val hostNavigation = StackNavigation<HostConfig>()

    override fun pop() = hostNavigation.pop()

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
            is HostConfig.About -> TODO()
            is HostConfig.Main -> HostComponent.HostChild.MainChild(
                MainScreenComponent(
                    navigateToAbout = { hostNavigation.pushNew(HostConfig.Todo("About")) },
                    navigateToSample1 = { hostNavigation.pushNew(HostConfig.Todo("Sample1")) },
                    navigateToSample2 = { hostNavigation.pushNew(HostConfig.Todo("Sample2")) },
                    componentContext = componentContext
                )
            )
            is HostConfig.Todo -> HostComponent.HostChild.TodoChild(
                TodoScreenComponent(
                    text = config.screenName,
                    componentContext = componentContext
                )
            )
        }

    @Serializable
    internal sealed class HostConfig {
        @Serializable
        data object Main : HostConfig()
        data object About : HostConfig()
        data class Todo(val screenName: String) : HostConfig()
    }
}
