import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include
import org.jetbrains.kotlin.js.backend.ast.JsEmpty.setSource

buildscript {
    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://dl.google.com/dl/android/maven2")
        }
    }
    dependencies {
        classpath (libs.build.logic.android.gradlePlugin)
        classpath (libs.build.logic.kotlin.gradlePlugin)
        classpath (libs.kotlin.serialization)
        classpath (libs.hilt.android.plugin)
    }
}

plugins {
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt)
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(file("${rootProject.projectDir}/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    basePath = projectDir.absolutePath
    reportsDir = file("$projectDir/build/reports/detekt/")
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}

tasks.register("detektAll", Detekt::class.java){
    val autoFix = project.hasProperty("detektAutoFix")

    description = "Custom DETEKT build for all modules"
    parallel = true
    ignoreFailures = true
    autoCorrect = autoFix
    buildUponDefaultConfig = true
    setSource(file(projectDir))
    config.setFrom(file("${rootProject.projectDir}/config/detekt/detekt.yml"))
    include("**/*.kt")
    exclude("**/resources/**", "**/build/**")
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        reportsDir = file("$projectDir/build/reports/detekt/")
    }
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        basePath = projectDir.absolutePath
        reportsDir = file("$projectDir/build/reports/detekt/")
    }
}

val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(files(rootDir))
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    baseline.set(file("$rootDir/config/detekt/baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}
