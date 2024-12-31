import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import vvchn.at.composempuisamples.androidCompileSdk
import vvchn.at.composempuisamples.androidMinSdk
import vvchn.at.composempuisamples.androidTargetSdk
import vvchn.at.composempuisamples.applyPlugin
import vvchn.at.composempuisamples.libs

class AndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.androidLibrary)

            extensions.configure<LibraryExtension> {
                compileSdk = androidCompileSdk
                defaultConfig.targetSdk = androidTargetSdk
                defaultConfig.minSdk = androidMinSdk

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }
        }
    }
}
