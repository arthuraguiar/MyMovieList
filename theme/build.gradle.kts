plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
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
    kotlinOptions {
        jvmTarget = "17"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {

    val composeBom = platform("androidx.compose:compose-bom:2024.04.00")
    implementation(composeBom)

    implementation(libs.androidx.appcompat)
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material:material")
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)
}
