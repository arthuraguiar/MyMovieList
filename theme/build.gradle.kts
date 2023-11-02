plugins {
    id("gohorse.android.library")
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {

    val composeBom = platform("androidx.compose:compose-bom:2023.04.01")
    implementation(composeBom)

    implementation(libs.androidx.appcompat)
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.compose.material:material")
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)
}
