plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val jsMain by getting {
        dependencies {
            api(project(":theme-react"))
            api(project(":tools-react"))
            api("org.jetbrains:kotlin-react:${versions.kotlinjs.react}")
        }
    }
}
