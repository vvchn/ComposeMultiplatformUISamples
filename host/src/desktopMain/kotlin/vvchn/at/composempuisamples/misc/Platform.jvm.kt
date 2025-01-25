package vvchn.at.composempuisamples.misc

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
data class JVMEnvironment(
    override val systemInfo: String = "${System.getProperty("os.name")} ${System.getProperty("os.arch")}",
    override val sdkInfo: String? = null,
    override val appVersion: String = "2025.0.1"
) : Environment

@Stable
actual fun getEnvironment(): Environment = JVMEnvironment()