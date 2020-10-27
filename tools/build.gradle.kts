plugins {
    id("asoft-lib")
    id("root-module")
}

android {
    defaultConfig {
        minSdkVersion(9)
    }
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(kotlin("stdlib-common"))
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${versions.kotlinx.serialization}")
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${versions.kotlinx.coroutines}")
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
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx.coroutines}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx.coroutines}")
        }
    }

    val jsMain by getting {
        dependencies {
            api(kotlin("stdlib-js"))
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${versions.kotlinx.serialization}")
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
            api("org.jetbrains:kotlin-extensions:${versions.kotlinjs.extensions}")
            api("org.jetbrains.kotlinx:kotlinx-html-js:${versions.kotlinx.html}")
            api("org.jetbrains:kotlin-css-js:${versions.kotlinjs.css}")
        }
    }
}
