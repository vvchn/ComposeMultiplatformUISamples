import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSymbolProcessing) apply false
    alias(libs.plugins.kotlinxSerialization) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.detektPlugin)
}

dependencies {
    detektPlugins(libs.detekt.compose)
    detektPlugins(libs.detekt.formatting)
}

tasks.register<Detekt>("detektRootAndBuildLogic") {
    group = "Verification"
    description = "Run custom detekt task (only for project root and build-logic)."

    basePath = project.rootDir.path
    reportsDir = file("$rootDir/detekt/reports/root")
//    baseline = file("$rootDir/detekt/baseline/baseline.xml")
    setSource(
        files(
            "$rootDir/build.gradle.kts",
            "$rootDir/settings.gradle.kts",
            "$rootDir/build-logic"
        )
    )

    allRules = true
    parallel = true
    ignoreFailures = true

    reports {
        xml.required.set(true)
        html.required.set(true)
        md.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
    }

    excludeModuleLevelFolders()
}

tasks.register<DetektCreateBaselineTask>("detektProjectBaseline") {
    group = "Verification"
    description = "Overrides current baseline."

    setSource(files(rootDir))
    baseline.set(file("$rootDir/detekt/baseline/baseline.xml"))
    config.setFrom(files("$rootDir/detekt/configs/compose-rules.yml"))

    buildUponDefaultConfig = true
    ignoreFailures = true
    parallel = true

    excludeProjectLevelFolders()
    excludeModuleLevelFolders()
}

private inline fun SourceTask.excludeProjectLevelFolders() {
    exclude(".gradle/")
    exclude(".idea/")
    exclude(".kotlin/")
    exclude("build/")
}

private inline fun SourceTask.excludeModuleLevelFolders() {
    exclude("**/build/**")
    exclude("**/resources/**")
    exclude("**/.gradle/**")
}
