pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")
include(":rx")
project(":rx").projectDir = File("../rx")
include(":persist")
project(":persist").projectDir = File("../persist")
include(":platform")
project(":platform").projectDir = File("../platform")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":io")
project(":io").projectDir = File("../io")