import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
    id("config.compose.library")
}

android {
    namespace = "com.example.theme"

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.material.android)
    implementation(libs.compose.material3)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)
}
