plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api(project(":primary:platform"))
            api(project(":secondary:auth"))
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
            api("com.google.firebase:firebase-core:${versions.firebase.core}")
            api("com.google.firebase:firebase-firestore-ktx:${versions.firebase.firestore}")
            api("com.google.firebase:firebase-auth:${versions.firebase.auth}")
            api("com.google.firebase:firebase-storage-ktx:${versions.firebase.storage}")
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
            // old working "firebase", "5.11.1"
        }
    }
}