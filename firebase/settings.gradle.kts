pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")

include(":io")
project(":io").projectDir = File("../io")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":tools")
project(":tools").projectDir = File("../tools")

include(":firebase-core")
include(":firebase-auth")
include(":firebase-storage")
include(":firebase-firestore")
