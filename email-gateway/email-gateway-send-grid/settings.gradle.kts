pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
includeBuild("../../test")
include(":klock")
project(":klock").projectDir = File("../../klock")
include(":email")
project(":email").projectDir = File("../../email")
include(":persist")
project(":persist").projectDir = File("../../persist")
include(":io")
project(":io").projectDir = File("../../io")
include(":http")
project(":http").projectDir = File("../../http")
include(":email-gateway-core")
project(":email-gateway-core").projectDir = File("../email-gateway-core")