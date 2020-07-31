plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${versions.kotlinx.serialization}")
            api(project(":firebase-core"))
            api(project(":tools-core"))
        }
    }

    val androidMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
            api("com.google.firebase:firebase-firestore-ktx:${versions.firebase.firestore}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
        }
    }

    val jsMain by getting {
        dependencies {
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${versions.kotlinx.serialization}")
        }
    }
}