plugins {
    `kotlin-dsl`
}

group = "br.com.gohorse.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.build.logic.android.gradlePlugin)
    compileOnly(libs.build.logic.kotlin.gradlePlugin)
}

gradlePlugin {

    plugins {

        register("androidLibrary") {
            id = "gohorse.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidHilt") {
            id = "gohorse.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}
