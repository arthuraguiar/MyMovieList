plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
    id("config.android.hilt")
}

android {
    namespace = "com.example.mymovieslist.data"
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.core.dataRemote)
    implementation(projects.core.domain)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
}