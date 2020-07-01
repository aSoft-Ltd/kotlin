plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(kotlin("stdlib-common"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${versions.kotlinx.coroutines}")
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${versions.kotlinx.serialization}")
            api("org.jetbrains.kotlinx:atomicfu-common:${versions.kotlinx.atomicfu}")
        }
    }

    val commonTest by getting {
        dependencies {
            api(asoft("test"))
        }
    }

    val androidMain by getting {
        dependencies {
            api(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlinx.coroutines}")
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
            api("org.jetbrains.kotlinx:atomicfu:${versions.kotlinx.atomicfu}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx.coroutines}")
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
            api("org.jetbrains.kotlinx:atomicfu:${versions.kotlinx.atomicfu}")
        }
    }

    val jsMain by getting {
        dependencies {
            api(kotlin("stdlib-js"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${versions.kotlinx.serialization}")
            api("org.jetbrains.kotlinx:atomicfu-js:${versions.kotlinx.atomicfu}")
        }
    }
}