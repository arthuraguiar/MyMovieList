package config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            val bom = libs.findLibrary("compose-boom").get()
            "implementation"(platform(bom))
            "androidTestImplementation"(platform(bom))
            "implementation"(libs.findLibrary("compose-ui-tooling-preview").get())
            "debugImplementation"(libs.findLibrary("compose-ui-tooling").get())
        }
    }
}