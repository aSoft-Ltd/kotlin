pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":viewmodel-core")
project(":viewmodel-core").projectDir = File("../viewmodel-core")
