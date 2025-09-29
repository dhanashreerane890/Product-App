package com.anywhere.convention.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import setBuildFeaturesOptions
import setKotlinCompilerOptions

@Suppress("NewApi")
class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {

                if (!hasPlugin("library.plugin")) {
                    apply("library.plugin")
                }


                target.extensions.configure(LibraryExtension::class.java) {
                    setBuildFeaturesOptions()
                    setKotlinCompilerOptions()
                    dependencies {
                        val versionCatalog = target.the<VersionCatalogsExtension>().named("libs")

                        add(
                            "implementation",
                            platform(versionCatalog.findLibrary("androidx-compose-bom").get())
                        )

                        add(
                            "implementation",
                            versionCatalog.findLibrary("androidx-activity-compose").get()
                        )
                        add(
                            "implementation", versionCatalog.findLibrary("androidx-ui").get()
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
                            "implementation", versionCatalog.findLibrary("androidx-material3").get()
                        )
                        add(
                            "implementation",
                            versionCatalog.findLibrary("androidx-ui-animation").get()
                        )
                        add(
                            "implementation",
                            versionCatalog.findLibrary("androidx-lifecycle-viewmodel-compose").get()
                        )
                        add(
                            "implementation", versionCatalog.findLibrary("coil-compose").get()
                        )
                    }
                }
            }
        }
    }
}


