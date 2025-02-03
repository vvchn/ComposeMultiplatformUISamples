package vvchn.at.composempuisamples.screens.about.license

import com.arkivanov.decompose.ComponentContext
import vvchn.at.composempuisamples.misc.Environment
import vvchn.at.composempuisamples.misc.getEnvironment
import vvchn.at.composempuisamples.mvi.Action
import vvchn.at.composempuisamples.mvi.Component

class LicenseScreenComponent(
    componentContext: ComponentContext
): Component<Action>(componentContext) {
    val environment: Environment = getEnvironment()
    val pathToLicense = "files/LICENSE.txt"
//    val license =
}