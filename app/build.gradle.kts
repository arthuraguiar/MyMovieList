import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization")
}

android {
    compileSdk = 33

    defaultConfig {

        applicationId = "com.example.mymovieslist"
        minSdk = 24
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.4.7"
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    implementation("com.google.dagger:hilt-android:2.44")
    implementation(project(mapOf("path" to ":theme")))
    implementation(project(mapOf("path" to ":core:common")))
    implementation(project(mapOf("path" to ":core:domain")))
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.28.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("app.cash.turbine:turbine:0.12.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
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