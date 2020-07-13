plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val jsMain by getting {
        dependencies {
            api(project(":theme-core"))
            api(project(":tools-react"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
        }
    }
}
