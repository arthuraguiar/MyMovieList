plugins {
    id("config.kmp.library")
    id("config.kmp.compose.library")
}

kotlin {
    android {
        namespace = "br.com.mymovielist.theme"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
        androidResources {
            enable = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.compose.material.android)
        }
    }
}
