// removed pluginManagement block for brevity
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "Product-App"
include(":app")
include(":network")
include(":product")
include(":product:product-listing")
include(":design-system")
include(":core")