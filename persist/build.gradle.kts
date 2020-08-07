plugins {
    id("asoft-lib")
    id("root-module")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:atomicfu-common:${versions.kotlinx.atomicfu}")
            api(project(":paging-core"))
        }
    }

    val androidMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:atomicfu:${versions.kotlinx.atomicfu}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:atomicfu:${versions.kotlinx.atomicfu}")
        }
    }

    val jsMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:atomicfu-js:${versions.kotlinx.atomicfu}")
        }
    }
}