pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":platform")
project(":platform").projectDir = File("../platform")