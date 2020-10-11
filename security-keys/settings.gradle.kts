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

include(":krypto")
project(":krypto").projectDir = File("../krypto")