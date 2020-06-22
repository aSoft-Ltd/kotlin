plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val jsMain by getting {
        dependencies {
            api("org.jetbrains:kotlin-react:${versions.kotlinjs.react}")
        }
    }
}