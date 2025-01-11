package vvchn.at.composempuisamples.screens.main

import com.arkivanov.decompose.ComponentContext
import vvchn.at.composempuisamples.mvi.Component

class MainScreenComponent(
    private val navigateToAbout: () -> Unit,
    private val navigateToSample1: () -> Unit,
    private val navigateToSample2: () -> Unit,
    componentContext: ComponentContext
): Component<MainScreenAction>(componentContext) {

    override fun handleIntent(intent: MainScreenAction) {
        return when(intent) {
            MainScreenAction.OpenAboutPage -> navigateToAbout()
            MainScreenAction.OpenSample1 -> navigateToSample1()
            MainScreenAction.OpenSample2 -> navigateToSample2()
        }
    }
}
