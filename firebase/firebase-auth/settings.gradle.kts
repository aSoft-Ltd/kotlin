pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":firebase-core")
project(":firebase-core").projectDir = File("../firebase-core")