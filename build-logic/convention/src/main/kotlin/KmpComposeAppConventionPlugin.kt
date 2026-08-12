import config.configureComposeMultiplatform
import config.configureKotlinMultiplatformAppTargets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpComposeAppConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.application")
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureKotlinMultiplatformAppTargets(this)
            }

            configureComposeMultiplatform()
        }
    }
}
