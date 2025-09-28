plugins {
    id("library.plugin")
}

android {
    namespace = "com.anywhere.logger.impl"
}

dependencies {
    implementation(projects.logger.api)
}