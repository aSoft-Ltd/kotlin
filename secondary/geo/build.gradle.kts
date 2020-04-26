import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    `asoft-lib`
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
            api(project(":primary:klock"))
            api(project(":primary:platform"))
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(project(":primary:test"))
        }
    }

    val androidMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlinx.coroutines}")
            api("com.google.android.gms:play-services-maps:${versions.google.maps}")
        }
    }

    val jvmMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx.coroutines}")
        }
    }

    val jsMain by getting {
        dependencies {
            implementation(kotlin("stdlib-js"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
        }
    }
}