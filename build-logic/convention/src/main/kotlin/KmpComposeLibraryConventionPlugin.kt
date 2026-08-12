import config.configureComposeMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpComposeLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            configureComposeMultiplatform()
        }
    }
}
