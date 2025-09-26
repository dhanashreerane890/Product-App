plugins {
    id("library.plugin")

}

android {
    namespace = "com.anywhere.network"
}

dependencies {

    // Retrofit + OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)
}