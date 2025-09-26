plugins {
    id("compose.library.plugin")
    id("room.library.plugin")
    id("common.dependency.plugin")
}

android {
    namespace = "com.anywhere.product"
}

dependencies {

    implementation(projects.network)
    implementation(projects.designSystem)
    implementation(projects.core)

    implementation(libs.retrofit)

}