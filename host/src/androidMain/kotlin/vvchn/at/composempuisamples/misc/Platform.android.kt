package vvchn.at.composempuisamples.misc

import android.os.Build
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
data class AndroidEnvironment(
    override val systemInfo: String = "${Build.MANUFACTURER} ${Build.MODEL}",
    override val sdkInfo: String? = "Android SDK ${Build.VERSION.SDK_INT}",
    override val appVersion: String = "2025.0.1"
) : Environment

@Stable
actual fun getEnvironment(): Environment = AndroidEnvironment()