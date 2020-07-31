plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":firebase-core"))
        }
    }

    val androidMain by getting {
        dependencies {
            api("com.google.firebase:firebase-auth:${versions.firebase.auth}")
        }
    }

    val jsMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
        }
    }
}