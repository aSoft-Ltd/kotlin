plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(kotlin("stdlib-common"))
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${versions.kotlinx.serialization}")
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
//            api("androidx.appcompat:appcompat:${versions.androidx.appcompat}")
            api("androidx.ui:ui-material:${versions.androidx.ui}")
            api("androidx.lifecycle:lifecycle-extensions:${versions.androidx.lifecycle}")
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
        }
    }

    val jvmMain by getting {
        dependencies {
            api(kotlin("stdlib"))
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions.kotlinx.serialization}")
        }
    }

    val jsMain by getting {
        dependencies {
            api(kotlin("stdlib-js"))
            api("org.jetbrains:kotlin-css-js:${versions.kotlinjs.css}")
            api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${versions.kotlinx.serialization}")
        }
    }
}
