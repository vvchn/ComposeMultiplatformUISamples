package vvchn.at.composempuisamples.screens.todo

import com.arkivanov.decompose.ComponentContext
import vvchn.at.composempuisamples.mvi.Action
import vvchn.at.composempuisamples.mvi.Component

class TodoScreenComponent(
    val text: String,
    componentContext: ComponentContext
) : Component<Action>(componentContext)