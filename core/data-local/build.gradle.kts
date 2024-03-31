plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
    id("config.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.data_local"

}

dependencies {

    implementation(projects.core.domain)
    implementation(libs.bundles.room)
    ksp(libs.room.runtime)
}