package vvchn.at.composempuisamples.screens.host

import vvchn.at.composempuisamples.screens.about.AboutScreenComponent
import vvchn.at.composempuisamples.screens.about.license.LicenseScreenComponent
import vvchn.at.composempuisamples.screens.main.MainScreenComponent
import vvchn.at.composempuisamples.screens.todo.TodoScreenComponent

sealed class HostChild {
    data class AboutChild(val component: AboutScreenComponent): HostChild()
    data class MainChild(val component: MainScreenComponent): HostChild()
    data class TodoChild(val component: TodoScreenComponent): HostChild()
    data class LicenseChild(val component: LicenseScreenComponent): HostChild()
}