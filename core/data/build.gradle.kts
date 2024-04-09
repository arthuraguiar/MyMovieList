plugins {
    alias(libs.plugins.config.android.library)
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.config.android.hilt)
}

android {
    namespace = "com.example.mymovieslist.data"
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.core.dataremote)
    implementation(projects.core.datalocal)
    implementation(projects.core.domain)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
}
