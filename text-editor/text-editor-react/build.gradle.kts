plugins {
    id("asoft-lib-browser")
}

kotlin.sourceSets {
    val main by getting {
        dependencies {
            api(project(":tools-core"))
            api(project(":react-core"))
            api(project(":text-editor-core"))
            api(project(":react-layouts"))
            api(project(":react-inputs"))
            api(project(":react-tabs"))
        }
    }
}
