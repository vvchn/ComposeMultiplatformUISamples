package vvchn.at.composempuisamples.misc

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import platform.UIKit.UIDevice

@Immutable
data class IOSEnvironment(
    override val systemInfo: String = "${UIDevice.currentDevice.model} ${UIDevice.currentDevice.systemVersion}",
    override val sdkInfo: String? = null,
    override val appVersion: String = "2025.0.1"
) : Environment

@Stable
actual fun getEnvironment(): Environment = IOSEnvironment()