package vvchn.at.composempuisamples.screens.host

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import vvchn.at.composempuisamples.mvi.NavComponent
import vvchn.at.composempuisamples.screens.about.AboutScreenComponent
import vvchn.at.composempuisamples.screens.main.MainScreenComponent
import vvchn.at.composempuisamples.screens.todo.TodoScreenComponent

class HostComponent(
    componentContext: ComponentContext,
) : NavComponent<HostContentAction, HostChild>(componentContext) {
    private val hostNavigation = StackNavigation<HostConfig>()

    override val controllerState: Value<ChildStack<*, HostChild>> =
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
    ): HostChild =
        when (config) {
            is HostConfig.About -> HostChild.AboutChild(
                AboutScreenComponent(componentContext = componentContext)
            )
            is HostConfig.Main -> HostChild.MainChild(
                MainScreenComponent(
                    navigateToAbout = { hostNavigation.pushNew(HostConfig.About) },
                    navigateToSample1 = { hostNavigation.pushNew(HostConfig.Todo("Sample1")) },
                    navigateToSample2 = { hostNavigation.pushNew(HostConfig.Todo("Sample2")) },
                    componentContext = componentContext
                )
            )
            is HostConfig.Todo -> HostChild.TodoChild(
                TodoScreenComponent(
                    text = config.screenName,
                    componentContext = componentContext
                )
            )
        }

    override fun handleIntent(intent: HostContentAction) {
        return when(intent) {
            HostContentAction.MoveBackByPressingTopBarBtn -> hostNavigation.pop()
        }
    }

    @Serializable
    private sealed class HostConfig {
        @Serializable data object Main : HostConfig()
        @Serializable data object About : HostConfig()
        @Serializable data class Todo(val screenName: String) : HostConfig()
    }
}
