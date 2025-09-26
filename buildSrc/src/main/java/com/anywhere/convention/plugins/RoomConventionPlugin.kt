package com.anywhere.convention.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

@Suppress("NewApi")
class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {

                if (!hasPlugin("library.plugin")) {
                    apply("library.plugin")
                }
                apply("kotlin-kapt")

                val versionCatalog = target.the<VersionCatalogsExtension>().named("libs")
                target.extensions.configure(LibraryExtension::class.java) {
                    dependencies {
                        add(
                            "implementation",
                            versionCatalog.findLibrary("room-runtime").get()
                        )
                        add(
                            "implementation",
                            versionCatalog.findLibrary("room-ktx").get()
                        )
                        add(
                            "kapt",
                            versionCatalog.findLibrary("room-compiler").get()
                        )
                    }
                }
            }
        }
    }
}