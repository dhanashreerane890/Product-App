package com.anywhere.convention.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import setupBuildTypes
import setupCompileOptions
import setupDefaultConfig
import setupKotlinOptions

@Suppress("NewApi")

class LibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")

                val versionCatalog = target.the<VersionCatalogsExtension>().named("libs")
                target.extensions.configure(LibraryExtension::class.java) {
                    setupDefaultConfig()
                    setupCompileOptions()
                    setupKotlinOptions()
                    setupBuildTypes()
                    dependencies {
                        add(
                            "implementation",
                            versionCatalog.findLibrary("androidx-core-ktx").get()
                        )
                        add(
                            "implementation",
                            versionCatalog.findLibrary("androidx-appcompat").get()
                        )
                        add(
                            "implementation",
                            versionCatalog.findLibrary("material").get()
                        )
                        add(
                            "testImplementation",
                            versionCatalog.findLibrary("junit").get()
                        )
                        add("androidTestImplementation", "androidx.test.ext:junit:1.1.5")


                        add(
                            "androidTestImplementation",
                            "androidx.test.espresso:espresso-core:3.5.1"
                        )
                    }
                }
            }
        }
    }
}