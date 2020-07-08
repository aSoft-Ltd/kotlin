pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
includeBuild("../../test")
include(":persist")
project(":persist").projectDir = File("../../persist")
