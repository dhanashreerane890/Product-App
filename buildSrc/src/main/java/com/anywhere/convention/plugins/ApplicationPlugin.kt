package com.anywhere.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import setBuildFeaturesOptions
import setKotlinCompilerOptions
import setupBuildTypes
import setupCompileOptions
import setupDefaultConfig
import setupKotlinOptions

@Suppress("NewApi")
class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            // Apply required plugins
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")


            extensions.configure<BaseAppModuleExtension> {
                setBuildFeaturesOptions()
                setKotlinCompilerOptions()
            }

            val versionCatalog = target.the<VersionCatalogsExtension>().named("libs")

            target.extensions.configure(ApplicationExtension::class.java) {
                setupDefaultConfig()
                setupCompileOptions()
                setupBuildTypes()
                setupKotlinOptions()
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

                    add(
                        "implementation",
                        platform(versionCatalog.findLibrary("androidx-compose-bom").get())
                    )

                    add(
                        "implementation",
                        versionCatalog.findLibrary("androidx-activity-compose").get()
                    )
                    add(
                        "implementation",
                        versionCatalog.findLibrary("androidx-ui").get()
                    )
                    add(
                        "implementation",
                        versionCatalog.findLibrary("androidx-ui-graphics").get()
                    )
                    add(
                        "implementation",
                        versionCatalog.findLibrary("androidx-ui-tooling").get()
                    )
                    add(
                        "implementation",
                        versionCatalog.findLibrary("androidx-ui-tooling-preview").get()
                    )
                    add(
                        "implementation",
                        versionCatalog.findLibrary("androidx-material3").get()
                    )
                    add(
                        "implementation",
                        versionCatalog.findLibrary("androidx-lifecycle-viewmodel-compose").get()
                    )
                    add(
                        "implementation",
                        versionCatalog.findLibrary("coil-compose").get()
                    )
                }
            }

        }
    }
}