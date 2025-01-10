package vvchn.at.composempuisamples.mvi

import com.arkivanov.decompose.ComponentContext

abstract class Component<A : Action>(
    private val componentContext: ComponentContext
) : ComponentContext by componentContext {
    private fun onIntent(intent: A) = handleIntent(intent)

    protected open fun handleIntent(intent: A) = Unit

    fun actionHandler(action: A) = onIntent(action)
}