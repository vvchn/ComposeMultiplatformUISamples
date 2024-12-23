import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import vvchn.at.composempuisamples.androidCompileSdk
import vvchn.at.composempuisamples.androidMinSdk
import vvchn.at.composempuisamples.androidTargetSdk
import vvchn.at.composempuisamples.androidVersionCode
import vvchn.at.composempuisamples.androidVersionName
import vvchn.at.composempuisamples.applyPlugin
import vvchn.at.composempuisamples.defaultPackageName
import vvchn.at.composempuisamples.libs

class AndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.androidApplication)

            extensions.configure<ApplicationExtension> {
                compileSdk = androidCompileSdk

                defaultConfig {
                    applicationId = defaultPackageName
                    minSdk = androidMinSdk
                    targetSdk = androidTargetSdk
                    versionCode = androidVersionCode
                    versionName = androidVersionName
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                buildTypes {
                    getByName("debug") {
                        versionNameSuffix = "_debug"
                        isDebuggable = true
                        isMinifyEnabled = false
                        isShrinkResources = false
                    }
                    getByName("release") {
                        isDebuggable = false
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }
        }
    }
}
