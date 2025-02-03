package vvchn.at.composempuisamples.misc

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
data class JVMEnvironment(
    override val systemInfo: String = getSystemInfo(),
    override val sdkInfo: String? = "Java ${System.getProperty("java.version")} ${System.getProperty("java.vendor")}",
    override val appVersion: String = "2025.0.1",
    override val isDesktop: Boolean = true
) : Environment

@Stable
actual fun getEnvironment(): Environment = JVMEnvironment()

@Stable
private fun getSystemInfo(): String {
    val osName = System.getProperty("os.name")
    val version = if (osName.contains("win", true)) "" else "${System.getProperty("os.version")} "
    val arch = System.getProperty("os.arch")
    return "$osName $version$arch"
}