package com.anywhere.convention.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

class CommonDependencyPlugin : Plugin<Project> {
    @Suppress("NewApi")
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {

                dependencies {
                    val versionCatalog = target.the<VersionCatalogsExtension>().named("libs")

                    // coroutines
                    add("implementation", versionCatalog.findLibrary("kotlinx-coroutines-core").get())
                    add("implementation", versionCatalog.findLibrary("kotlinx-coroutines-android").get())

                    // androidx lifecycle
                    add("implementation",versionCatalog.findLibrary("androidx-lifecycle-viewmodel-ktx").get())
                    add("implementation",versionCatalog.findLibrary("androidx-lifecycle-runtime-ktx").get())

                }
            }
        }
    }
}