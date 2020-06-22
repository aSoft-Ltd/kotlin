pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
includeBuild("../../test")

include(":firebase-core")
project(":firebase-core").projectDir = File("../firebase-core")
include(":klock")
project(":klock").projectDir = File("../../klock")
include(":io")
project(":io").projectDir = File("../../io")