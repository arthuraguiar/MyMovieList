plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.common"
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.coroutines.android)
    implementation(project(mapOf("path" to ":core:domain")))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
