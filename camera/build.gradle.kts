plugins {
    id("asoft-lib")
}

android {
    defaultConfig {
        minSdkVersion(17)
    }
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api(project(":klock"))
            api(project(":ui"))
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(asoft("test"))
        }
    }

    val androidMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api("androidx.camera:camera-core:${versions.androidx.camerax}")
            api("androidx.camera:camera-camera2:${versions.androidx.camerax}")
            api("androidx.constraintlayout:constraintlayout:${versions.androidx.constraint}")
            api("androidx.recyclerview:recyclerview-selection:${versions.androidx.recyclerview}")
            api("androidx.recyclerview:recyclerview:${versions.androidx.recyclerview}")
            api("io.coil-kt:coil:${versions.coil}") {
                exclude(group = "androidx.appcompat")
            }
        }
    }

    val jvmMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
        }
    }

    val jsMain by getting {
        dependencies {
            implementation(kotlin("stdlib-js"))
        }
    }
}