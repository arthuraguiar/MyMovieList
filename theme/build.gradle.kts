import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("config.android.library")
    id("config.compose.library")
}

configure<com.android.build.api.dsl.LibraryExtension> {
    namespace = "br.com.mymovielist.theme"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.material.android)
    implementation(libs.compose.material3)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)
}
