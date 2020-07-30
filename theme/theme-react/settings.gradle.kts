pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":theme-core")
project(":theme-core").projectDir = File("../theme-core")
