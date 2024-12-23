import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import vvchn.at.composempuisamples.applyPlugin
import vvchn.at.composempuisamples.debugImplementation
import vvchn.at.composempuisamples.defaultPackageName
import vvchn.at.composempuisamples.desktopMain
import vvchn.at.composempuisamples.libs
import vvchn.at.composempuisamples.winVersion

class CmpConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.composeMultiplatform)
            applyPlugin(libs.plugins.composeCompiler)

            val composeExtension = extensions.getByType<ComposeExtension>()
            val composeDependencies = composeExtension.dependencies

            composeExtension.extensions.configure<DesktopExtension> {
                application {
                    mainClass = "$defaultPackageName.MainKt"

                    nativeDistributions {
                        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                        packageName = "$defaultPackageName.${projectDir.name}"
                        packageVersion = winVersion
                    }
                }
            }

            extensions.configure<ComposeCompilerGradlePluginExtension> {
                includeSourceInformation.set(true)
                featureFlags.set(
                    setOf(
                        ComposeFeatureFlag.StrongSkipping,
                        ComposeFeatureFlag.OptimizeNonSkippingGroups
                    )
                )
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    androidMain.dependencies {
                        implementation(composeDependencies.preview)
                    }
                    commonMain.dependencies {
                        implementation(composeDependencies.runtime)
                        implementation(composeDependencies.foundation)
                        implementation(composeDependencies.material3)
                        implementation(composeDependencies.ui)
                        implementation(composeDependencies.components.resources)
                        implementation(composeDependencies.components.uiToolingPreview)
                    }
                    desktopMain.dependencies {
                        implementation(composeDependencies.desktop.currentOs)
                    }
                }
            }

            dependencies {
                debugImplementation(composeDependencies.uiTooling)
            }
        }
    }
}
