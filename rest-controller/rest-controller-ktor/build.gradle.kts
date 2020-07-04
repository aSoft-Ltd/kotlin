plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":rest-controller-core"))
            api(project(":result"))
        }
    }

    val androidMain by getting {
        dependencies {
            api("io.ktor:ktor-server-cio:${versions.ktor}")
            api("io.ktor:ktor-network:${versions.ktor}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api("io.ktor:ktor-server-cio:${versions.ktor}")
            api("io.ktor:ktor-network:${versions.ktor}")
        }
    }

    val jsMain by getting {
        dependencies {
        }
    }
}