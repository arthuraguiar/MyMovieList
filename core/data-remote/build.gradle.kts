import java.util.Properties
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

plugins {
    id("config.kmp.library")
    kotlin("plugin.serialization")
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.ksp)
}

kotlin {
    android {
        namespace = "br.com.mymovieslist.dataremote"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
        withHostTestBuilder {}.configure {}
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)
            implementation(libs.ktorfit.lib)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
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

dependencies {
    add("kspCommonMainMetadata", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspIosArm64", libs.ktorfit.ksp)
    add("kspIosSimulatorArm64", libs.ktorfit.ksp)
}

@CacheableTask
abstract class GenerateSecretsTask : DefaultTask() {

    // Applied in order, each overriding keys from the previous one - mirrors the old Android
    // secrets-gradle-plugin, which let a personal local.properties override the committed
    // defaults, and an optional secrets.properties override both.
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val secretsFiles: ConfigurableFileCollection

    @get:Input
    abstract val packageName: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val properties = Properties()
        secretsFiles.files.filter { it.exists() }.forEach { file ->
            file.inputStream().use { properties.load(it) }
        }
        val packageDir = outputDir.get().dir(packageName.get().replace(".", "/")).asFile
        packageDir.mkdirs()
        // Values in the properties files already include their own quotes, matching the
        // convention the previous Android secrets-gradle-plugin setup used.
        packageDir.resolve("Secrets.kt").writeText(
            "package ${packageName.get()}\n\n" +
                "internal object Secrets {\n" +
                "    const val API_KEY = ${properties.getProperty("API_KEY")}\n" +
                "    const val API_URL = ${properties.getProperty("API_URL")}\n" +
                "}\n"
        )
    }
}

val generateSecrets = tasks.register<GenerateSecretsTask>("generateSecrets") {
    secretsFiles.from(
        rootProject.file("secrets.defaults.properties"),
        rootProject.file("local.properties"),
        rootProject.file("secrets.properties"),
    )
    packageName.set("br.com.mymovieslist.dataremote")
    outputDir.set(layout.buildDirectory.dir("generated/source/secrets/commonMain/kotlin"))
}

kotlin {
    sourceSets.commonMain {
        kotlin.srcDir(generateSecrets.map { it.outputDir })
        // Ktorfit's KSP processor generates createMovieService() for the commonMain metadata
        // target too (kspCommonMainMetadata dependency above), but doesn't wire its output
        // into commonMain's own source set automatically, so it's added explicitly here.
        kotlin.srcDir(layout.buildDirectory.dir("generated/ksp/metadata/commonMain/kotlin"))
    }
}

// Task dependency is added by name (lazily) rather than via tasks.named(), because the new
// AGP com.android.kotlin.multiplatform.library plugin registers KSP's per-target compile
// tasks later than this script's configuration/afterEvaluate phase.
tasks.configureEach {
    if (name.startsWith("compile") || name.startsWith("ksp")) {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }
}
