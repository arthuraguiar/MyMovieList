package config

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform() {
    val kotlin = extensions.getByType<KotlinMultiplatformExtension>()
    val compose = ComposePlugin.Dependencies(this)

    kotlin.sourceSets.getByName("commonMain").dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.materialIconsExtended)
        implementation(compose.ui)
        implementation(compose.components.resources)
        implementation(
            "org.jetbrains.compose.ui:ui-tooling-preview:${libs.findVersion("compose-multiplatform").get()}"
        )
    }
}
