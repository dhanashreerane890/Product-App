plugins {
    // 1. Apply the Kotlin DSL plugin to enable writing build logic in Kotlin
    `kotlin-dsl`
}

gradlePlugin {
    plugins {

        create("libraryPlugin") {
            id = "library.plugin"
            implementationClass = "com.anywhere.convention.plugins.LibraryPlugin"
        }
        create("applicationPlugin") {
            id = "application.plugin"
            implementationClass = "com.anywhere.convention.plugins.ApplicationPlugin"
        }
        create("roomLibraryPlugin") {
            id = "room.library.plugin"
            implementationClass = "com.anywhere.convention.plugins.RoomConventionPlugin"
        }
        create("composeLibraryPlugin") {
            id = "compose.library.plugin"
            implementationClass = "com.anywhere.convention.plugins.ComposeConventionPlugin"
        }

        create("commonDependencyPlugin") {
            id = "common.dependency.plugin"
            implementationClass = "com.anywhere.convention.plugins.CommonDependencyPlugin"
        }

    }
}


// 2. Declare dependencies for the build logic
dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
}
