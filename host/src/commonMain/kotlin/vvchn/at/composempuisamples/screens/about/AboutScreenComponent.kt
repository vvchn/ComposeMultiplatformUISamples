package vvchn.at.composempuisamples.screens.about

import com.arkivanov.decompose.ComponentContext
import vvchn.at.composempuisamples.misc.Environment
import vvchn.at.composempuisamples.misc.getEnvironment
import vvchn.at.composempuisamples.mvi.Component

class AboutScreenComponent(
    private val showLicense: () -> Unit,
    componentContext: ComponentContext
): Component<AboutScreenAction>(componentContext) {
    val environment: Environment = getEnvironment()
    val githubLink: String = "https://github.com/vvchn"

    override fun handleIntent(intent: AboutScreenAction) {
        return when (intent) {
            AboutScreenAction.ShowLicense -> showLicense()
        }
    }
}
