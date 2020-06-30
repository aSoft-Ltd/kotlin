pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")
include(":viewmodel")
project(":viewmodel").projectDir = File("../viewmodel")