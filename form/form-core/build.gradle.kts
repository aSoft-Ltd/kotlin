plugins {
    id("asoft-lib-browser-mpp")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies{
            api(project(":tools"))
        }
    }
}