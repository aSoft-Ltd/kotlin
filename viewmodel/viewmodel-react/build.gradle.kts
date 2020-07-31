plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val jsMain by getting {
        dependencies {
            api(project(":viewmodel-core"))
            api(project(":tools-react"))
        }
    }
}
