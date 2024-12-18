package vvchn.at.composempuisamples

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val NamedDomainObjectContainer<KotlinSourceSet>.desktopMain: KotlinSourceSet
    get() = this["desktopMain"]
