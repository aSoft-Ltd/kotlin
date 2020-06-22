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
include(":phone")
project(":phone").projectDir = File("../phone")
include(":persist")
project(":persist").projectDir = File("../persist")