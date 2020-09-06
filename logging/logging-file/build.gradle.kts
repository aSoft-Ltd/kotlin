plugins {
    id("asoft-lib-jvm")
}

kotlin.sourceSets {
    val main by getting {
        dependencies {
            api(project(":klock"))
            api(project(":logging-core"))
        }
    }
}