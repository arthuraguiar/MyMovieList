plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
    id("config.android.hilt")
}

android {
    namespace = "com.example.domain"
}

dependencies {
    testImplementation(libs.junit)
    implementation(libs.kotlinx.coroutines.android)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
}
