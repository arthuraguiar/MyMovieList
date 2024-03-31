plugins {
    alias(libs.plugins.config.android.library)
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.config.android.hilt)
    alias(libs.plugins.config.android.room)
}

android {
    namespace = "com.example.data_local"
}

dependencies {

}
