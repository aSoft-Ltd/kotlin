plugins {
    id("asoft-lib")
}

android {
    defaultConfig {
        minSdkVersion(9)
    }
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${versions.kotlinx.coroutines}")
            api(project(":viewmodel"))
        }
    }

    val androidMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlinx.coroutines}")
            api("androidx.lifecycle:lifecycle-extensions:${versions.androidx.lifecycle}")
            api("androidx.fragment:fragment:${versions.androidx.fragment}")
            api("androidx.appcompat:appcompat:${versions.androidx.appcompat}")
            api("com.google.code.gson:gson:${versions.gson}")
        }
    }

    val jvmMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx.coroutines}")
            api("no.tornado:tornadofx:${versions.tornadofx}")
        }
    }

    val jsMain by getting {
        dependencies {
            implementation(kotlin("stdlib-js"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
            api("org.jetbrains.kotlinx:kotlinx-html-js:${versions.kotlinx.html}")
            api("org.jetbrains:kotlin-extensions:${versions.kotlinjs.extensions}")
            api("org.jetbrains:kotlin-react:${versions.kotlinjs.react}")
        }
    }
}