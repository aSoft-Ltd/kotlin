plugins {
    id("asoft-lib")
    id("root-module")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":klock"))
            api(project(":persist"))
        }
    }
}