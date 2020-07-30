plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(kotlin("stdlib-common"))
        }
    }

    val androidMain by getting {
        dependencies {
            api(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlinx.coroutines}")
            api("com.google.firebase:firebase-core:${versions.firebase.core}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api(kotlin("stdlib"))
        }
    }

    val jsMain by getting {
        dependencies {
            api(kotlin("stdlib-js"))
        }
    }
}