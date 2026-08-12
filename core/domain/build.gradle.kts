plugins {
    id("config.kmp.library")
}

kotlin {
    android {
        namespace = "br.com.domain"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
        withHostTestBuilder {}.configure {}
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
        }
        getByName("androidHostTest").dependencies {
            implementation(libs.junit)
            implementation(libs.mockk)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.turbine)
            implementation(libs.kotlin.test)
        }
    }
}
