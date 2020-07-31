plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val jsMain by getting {
        dependencies {
            api(project(":theme-core"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
            api("org.jetbrains:kotlin-react:${versions.kotlinjs.react}")
            api("org.jetbrains:kotlin-styled:${versions.kotlinjs.styled}")
        }
    }
}
