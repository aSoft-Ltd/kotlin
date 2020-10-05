pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")

include(":krypto")
project(":krypto").projectDir = File("../krypto")

include(":mapper")
project(":mapper").projectDir = File("../mapper")

include(":klock")
project(":klock").projectDir = File("../klock")

include(":rsa")
project(":rsa").projectDir = File("../rsa")

include(":jwt-core")
include(":jwt-hs")
include(":jwt-rs")