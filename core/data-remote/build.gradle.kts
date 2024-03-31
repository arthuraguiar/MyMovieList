plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
    id("config.android.hilt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.mymovieslist.data_remote"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)
}