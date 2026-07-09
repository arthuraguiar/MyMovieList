plugins {
    id("config.android.library")
}

configure<com.android.build.api.dsl.LibraryExtension> {
    namespace = "br.com.mymovielist.common"
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.coroutines.android)
    implementation(project(mapOf("path" to ":core:domain")))

    testImplementation(libs.junit)
}
