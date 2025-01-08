import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import vvchn.at.composempuisamples.applyPlugin
import vvchn.at.composempuisamples.desktopMain
import vvchn.at.composempuisamples.iosBinariesBaseName
import vvchn.at.composempuisamples.javaVersion
import vvchn.at.composempuisamples.libs

class KmpConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.kotlinMultiplatform)
            applyPlugin(libs.plugins.kotlinxSerialization)

            extensions.configure<KotlinMultiplatformExtension> {
                jvmToolchain {
                    languageVersion.set(JavaLanguageVersion.of(javaVersion))
                }

                androidTarget {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                        freeCompilerArgs.add("-Xjdk-release=$javaVersion")
                    }
                }

                jvm("desktop") {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                        freeCompilerArgs.add("-Xjdk-release=$javaVersion")
                    }
                }

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = iosBinariesBaseName
                        isStatic = true
                    }
                }

                sourceSets.apply {
                    androidMain.dependencies {
                        implementation(libs.androidx.activity.compose)
                    }
                    commonMain.dependencies {
                        implementation(libs.androidx.lifecycle.viewmodel)
                        implementation(libs.androidx.lifecycle.runtime.compose)
                        implementation(libs.kotlinx.serialization)
                        implementation(libs.decompose)
                        implementation(libs.decompose.multiplatform)
                    }
                    desktopMain.dependencies {
                        implementation(libs.kotlinx.coroutines.swing)
                    }
                }
            }
        }
    }
}
