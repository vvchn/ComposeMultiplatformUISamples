import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceTask
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainService
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import vvchn.at.composempuisamples.applyPlugin
import vvchn.at.composempuisamples.detektPlugins
import vvchn.at.composempuisamples.detektVersion
import vvchn.at.composempuisamples.javaVersion
import vvchn.at.composempuisamples.libs

/**
 * Warning "classpath entry points to a non-existent location" can safely be ignored.
 *
 * [See](https://detekt.dev/docs/gettingstarted/gradle/) for more information.
*/
class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.detektPlugin)

            val javaToolchainService = project.extensions.getByType<JavaToolchainService>()
            val jdkPath = javaToolchainService.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
            }.get().metadata.installationPath.asFile.absolutePath

            val mergeDetektReportsTaskName = "mergeDetektReports"
            val mergeDetektReports = if (rootProject.tasks.findByName(mergeDetektReportsTaskName) != null) {
                rootProject.tasks.named<ReportMergeTask>(mergeDetektReportsTaskName)
            } else {
                rootProject.tasks.register<ReportMergeTask>(mergeDetektReportsTaskName).apply {
                    configure {
                        output.set(file("${project.rootDir}/detekt/reports/subprojects-merged.xml"))
                    }
                }
            }

            extensions.getByType<DetektExtension>().apply {
                toolVersion = detektVersion
                source.from(projectDir)
                config.setFrom("${project.rootDir}/detekt/configs/compose-rules.yml")
//                baseline = file("${project.rootDir}/detekt/baseline/baseline.xml")
                basePath = project.rootDir.path

                allRules = true
                buildUponDefaultConfig = true
                parallel = true
                ignoreFailures = true
            }

            dependencies {
                detektPlugins(libs.detekt.compose)
                detektPlugins(libs.detekt.formatting)
            }

            tasks.withType<Detekt>().configureEach {
                val taskName = this.name

                this.jvmTarget = javaVersion
                jdkHome.set(file(jdkPath))

                excludeDirs()

                reports {
                    html {
                        required.set(true)
                        outputLocation.set(file("${project.rootDir}/detekt/reports/${projectDir.name}/$taskName.html"))
                    }
                    xml.required.set(true)
                    md.required.set(false)
                    txt.required.set(false)
                    sarif.required.set(false)
                }

                mergeDetektReports.configure {
                    input.from(xmlReportFile)
                }

                finalizedBy(mergeDetektReports)
            }

            tasks.register("detektAll") {
                group = "Verification"
                description = "Run all detekt tasks with and without type resolution."

                dependsOn(tasks.withType<Detekt>())
            }
        }
    }
}

private fun SourceTask.excludeDirs() {
    exclude("**/resources/**")
    exclude("**/generated/**")
    exclude("**/.gradle/**")
}
