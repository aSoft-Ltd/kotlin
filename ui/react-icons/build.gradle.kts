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