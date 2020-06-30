plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(kotlin("stdlib-common"))
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
            api("androidx.ui:ui-material:${versions.androidx.ui}")
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
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
            api("org.jetbrains:kotlin-css-js:${versions.kotlinjs.css}")
        }
    }
}
