pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":io")
project(":io").projectDir = File("../io")
include(":http")
project(":http").projectDir = File("../http")
include(":persist")
project(":persist").projectDir = File("../persist")
include(":result")
project(":result").projectDir = File("../result")