pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":persist")
project(":persist").projectDir = File("../../persist")
include(":paging-core")
project(":paging-core").projectDir = File("../../paging/paging-core")