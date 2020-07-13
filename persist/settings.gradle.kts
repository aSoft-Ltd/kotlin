pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
include(":paging-core")
project(":paging-core").projectDir = File("../paging/paging-core")