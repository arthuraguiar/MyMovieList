plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
    id("config.android.hilt")
    kotlin("plugin.serialization")
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.example.mymovieslist.data_remote"

    secrets {
        defaultPropertiesFileName = "secrets.defaults.properties"
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)
    api(projects.core.common)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
}