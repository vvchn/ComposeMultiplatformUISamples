package vvchn.at.composempuisamples.mvi

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

abstract class NavComponent<A : Action, C : Any>(
    componentContext: ComponentContext
) : Component<A>(componentContext) {

    abstract val controllerState: Value<ChildStack<*, C>>

}
