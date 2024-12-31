package vvchn.at.composempuisamples

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

internal val Project.libs: LibrariesForLibs
    get() = extensions.getByType()

internal fun Project.applyPlugin(plugin: Provider<PluginDependency>) {
    this.pluginManager.apply(plugin.get().pluginId)
}

internal val Project.javaVersion: String
    get() = libs.versions.java.get()

internal val Project.androidCompileSdk: Int
    get() = libs.versions.androidCompileSdk.get().toInt()

internal val Project.androidMinSdk: Int
    get() = libs.versions.androidMinSdk.get().toInt()

internal val Project.androidTargetSdk: Int
    get() = libs.versions.androidTargetSdk.get().toInt()

internal val Project.winVersion: String
    get() = libs.versions.winVersion.get()

internal val Project.detektVersion: String
    get() = libs.versions.detekt.get()
