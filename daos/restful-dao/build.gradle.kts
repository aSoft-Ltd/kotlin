plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":io"))
            api(project(":form-http"))
            api(project(":persist"))
            api(project(":result"))
        }
    }
}