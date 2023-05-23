// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.0.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.7.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}