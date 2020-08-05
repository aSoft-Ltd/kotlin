plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val jsMain by getting {
        dependencies {
            api(project(":paging-core"))
            api(project(":enterprise-react"))
        }
    }
}
