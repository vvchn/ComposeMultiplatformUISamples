package vvchn.at.composempuisamples.misc

import androidx.compose.runtime.Stable

@Stable
interface Environment {
    val systemInfo: String
    val sdkInfo: String?
    val appVersion: String
    val isDesktop: Boolean
}

@Stable
expect fun getEnvironment(): Environment
