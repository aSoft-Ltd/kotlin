import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":persist"))
        }
    }
}