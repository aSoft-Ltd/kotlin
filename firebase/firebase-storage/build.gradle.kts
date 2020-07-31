plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":firebase-core"))
            api(project(":io"))
        }
    }

    val androidMain by getting {
        dependencies {
            api("com.google.firebase:firebase-storage-ktx:${versions.firebase.storage}")
        }
    }
}