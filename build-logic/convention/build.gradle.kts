import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "br.com.config.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.build.logic.android.gradlePlugin)
    compileOnly(libs.build.logic.kotlin.gradlePlugin)
    compileOnly(libs.compose.multiplatform.gradlePlugin)
}

gradlePlugin {

    plugins {

        register("kmpLibrary") {
            id = "config.kmp.library"
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("kmpComposeLibrary") {
            id = "config.kmp.compose.library"
            implementationClass = "KmpComposeLibraryConventionPlugin"
        }
        register("kmpComposeApp") {
            id = "config.kmp.compose.app"
            implementationClass = "KmpComposeAppConventionPlugin"
        }
    }
}
