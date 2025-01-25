package vvchn.at.composempuisamples.screens.about

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import vvchn.at.composempuisamples.misc.Environment
import vvchn.at.composempuisamples.misc.getEnvironment
import vvchn.at.composempuisamples.mvi.Action
import vvchn.at.composempuisamples.mvi.Component

class AboutScreenComponent(
    componentContext: ComponentContext
): Component<Action>(componentContext) {
    // TODO: State?
    val environment: Environment = getEnvironment()
    val urls = Urls()
}

@Immutable
data class Urls(
    val githubLink: String = "https://github.com/vvchn",
    val licenseLink: String = "https://www.anekdot.ru/id/1192721/"
)