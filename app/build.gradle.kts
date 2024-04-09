plugins {
    id("com.android.application")
    kotlin("android")
    alias(libs.plugins.config.android.hilt)
    kotlin("plugin.serialization")
    alias(libs.plugins.secrets)
}

android {
    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.mymovieslist"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    namespace = "com.example.mymovieslist"
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

    implementation(platform(libs.compose.boom))
    implementation(libs.bundles.compose)

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(projects.theme)
    implementation(projects.core.domain)
    implementation(projects.core.common)
    implementation(projects.core.dataremote)
    implementation(projects.core.data)
    implementation(projects.core.datalocal)

    implementation(libs.coil)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
