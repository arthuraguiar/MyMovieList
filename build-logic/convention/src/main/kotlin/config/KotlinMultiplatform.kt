package config

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatformTargets(
    extension: KotlinMultiplatformExtension,
) {
    extension.apply {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
    }
}

// com.android.application has no Kotlin-Multiplatform-native replacement yet, so the app
// module still configures its Android target the classic way instead of via `android {}`.
internal fun Project.configureKotlinMultiplatformAppTargets(
    extension: KotlinMultiplatformExtension,
) {
    extension.apply {
        androidTarget {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
        iosX64()
        iosArm64()
        iosSimulatorArm64()
    }
}
