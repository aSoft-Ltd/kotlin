pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
includeBuild("../../test")
include(":theme-core")
project(":theme-core").projectDir = File("../theme-core")
