import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    `asoft-lib`
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api("io.ktor:ktor-client-core:${versions.ktor}")
            api(project(":primary:io"))
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(project(":primary:test"))
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