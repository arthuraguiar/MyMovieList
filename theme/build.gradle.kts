plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.theme"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.1")
}