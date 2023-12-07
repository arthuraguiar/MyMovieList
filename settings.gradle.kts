pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
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
