plugins {
    id("config.android.library")
    id("config.android.hilt")
}

configure<com.android.build.api.dsl.LibraryExtension> {
    namespace = "com.example.domain"
}

dependencies {
    testImplementation(libs.junit)
    implementation(libs.kotlinx.coroutines.android)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
}
