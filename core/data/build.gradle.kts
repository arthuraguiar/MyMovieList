plugins {
    id("config.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.mymovieslist.data"
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.core.dataRemote)
}