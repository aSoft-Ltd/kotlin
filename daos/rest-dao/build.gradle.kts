import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(kotlin("stdlib-common"))
            api("io.ktor:ktor-client-core:${versions.ktor}")
            api(project(":io"))
            api(project(":http"))
            api(project(":persist"))
            api(project(":result"))
        }
    }

    val androidMain by getting {
        dependencies {
            api(kotlin("stdlib"))
            api("io.ktor:ktor-client-android:${versions.ktor}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api(kotlin("stdlib"))
            api("io.ktor:ktor-client-cio:${versions.ktor}")
        }
    }

    val jsMain by getting {
        dependencies {
            api(kotlin("stdlib-js"))
            api("io.ktor:ktor-client-js:${versions.ktor}")
        }
    }
}