plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api("io.ktor:ktor-client-core:${versions.ktor}")
            api(project(":io"))
        }
    }

    val androidMain by getting {
        dependencies {
            api("io.ktor:ktor-client-android:${versions.ktor}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api("io.ktor:ktor-client-cio:${versions.ktor}")
        }
    }

    val jsMain by getting {
        dependencies {
            api("io.ktor:ktor-client-js:${versions.ktor}")
        }
    }
}