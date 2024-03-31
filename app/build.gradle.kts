import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    id("config.android.hilt")
    kotlin("plugin.serialization")
}

android {
    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.mymovieslist"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_URL", "\"https://api.themoviedb.org/3/movie/\"")
            buildConfigField("String", "API_KEY", getApiKey())
        }
        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "API_URL", "\"https://api.themoviedb.org/3/movie/\"")
            isDebuggable = true
            buildConfigField("String", "API_KEY", getApiKey())
        }
    }
    namespace = "com.example.mymovieslist"
}

dependencies {

    implementation(platform("androidx.compose:compose-bom:2022.12.00"))
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui-tooling")

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(projects.theme)
    implementation(projects.core.domain)
    implementation(projects.core.common)
    implementation(projects.core.dataRemote)
    implementation(projects.core.data)

    implementation(libs.coil)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

fun getApiKey(): String {
    val fl = rootProject.file("gradle.properties")

    if (fl.exists()) {
        val properties = Properties()
        properties.load(FileInputStream(fl))
        return properties.getProperty("API_KEY")
    } else {
        throw FileNotFoundException()
    }
}
