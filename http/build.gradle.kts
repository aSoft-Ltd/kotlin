import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api("io.ktor:ktor-client-core:${versions.ktor}")
            api(project(":io"))
        }
    }

    val androidMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api("io.ktor:ktor-client-android:${versions.ktor}")
        }
    }

    val jvmMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api("io.ktor:ktor-client-cio:${versions.ktor}")
        }
    }

    val jsMain by getting {
        dependencies {
            implementation(kotlin("stdlib-js"))
            api("io.ktor:ktor-client-js:${versions.ktor}")
        }
    }
}