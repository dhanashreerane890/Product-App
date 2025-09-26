import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun ApplicationExtension.setupDefaultConfig() {
    namespace = "com.anywhere.product_app"
    compileSdk = 36
    defaultConfig {
        minSdk = 21
        targetSdk = 34
        applicationId = "com.anywhere.product_app"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

fun ApplicationExtension.setupCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

fun ApplicationExtension.setupKotlinOptions() {
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
        jvmTarget = "11"
    }
}

fun ApplicationExtension.setupBuildTypes() {
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

fun BaseAppModuleExtension.setBuildFeaturesOptions() {
    buildFeatures {
        compose = true
    }
}

fun BaseAppModuleExtension.setKotlinCompilerOptions() {
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}






