plugins {
    id("js-only-lib")
}

kotlin.sourceSets {
    val main by getting {
        dependencies {
            api("org.jetbrains:kotlin-react:${versions.kotlinjs.react}")
        }
    }
}

publishing {
    repositories {
        maven("https://maven.jetbrains.space/asofttz/kotlin") {
            credentials {
                username = "andylamax"
                password = "andymamson"
            }
        }
    }
}