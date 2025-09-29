import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun LibraryExtension.setupDefaultConfig() {
    compileSdk = 36
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

fun LibraryExtension.setupCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

fun LibraryExtension.setupKotlinOptions() {
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
        jvmTarget = "11"
    }


}

fun LibraryExtension.setKotlinCompilerOptions() {
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}


fun LibraryExtension.setBuildFeaturesOptions() {
    buildFeatures {
        compose = true
    }
}

fun LibraryExtension.setupBuildTypes() {
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


