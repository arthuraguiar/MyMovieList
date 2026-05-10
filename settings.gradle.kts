pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MyMoviesList"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include (":app")
include(":theme")
include(":core:common")
include(":core:domain")
include(":core:data-remote")
include(":core:data")
