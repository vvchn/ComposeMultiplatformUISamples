import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "vvchn.at.composempuisamples.buildlogic"

dependencies {
    // TODO: Delete when Issue https://github.com/gradle/gradle/issues/15383 is resolved.
    // Workaround for version catalog to work inside convention plugin
    // Error "Unresolved reference to version catalog" can safely be ignored
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    compileOnly(libs.gradleplugin.kotlin)
    compileOnly(libs.gradleplugin.android)
    compileOnly(libs.gradleplugin.compose)
    compileOnly(libs.gradleplugin.composeCompiler)
    compileOnly(libs.gradleplugin.detekt)
}

private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.java.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
}

gradlePlugin {
    plugins {
        register("kmpConvention") {
            id = "vvchn.at.composempuisamples.kmpconvention"
            implementationClass = "KmpConventionPlugin"
        }
        register("cmpConvention") {
            id = "vvchn.at.composempuisamples.cmpconvention"
            implementationClass = "CmpConventionPlugin"
        }
        register("androidConventionPlugin") {
            id = "vvchn.at.composempuisamples.androidconvention"
            implementationClass = "AndroidConventionPlugin"
        }
        register("detektConventionPlugin") {
            id = "vvchn.at.composempuisamples.detektconvention"
            implementationClass = "DetektConventionPlugin"
        }
    }
}
