pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":persist")
project(":persist").projectDir = File("../../persist")
include(":result")
project(":result").projectDir = File("../../result")