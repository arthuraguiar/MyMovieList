plugins {
    id("config.android.library")
    id("config.android.hilt")
}

configure<com.android.build.api.dsl.LibraryExtension> {
    namespace = "com.example.mymovieslist.data"
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.core.dataRemote)
    implementation(projects.core.domain)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
}