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
include(":io")
project(":io").projectDir = File("../../io")
include(":phone")
project(":phone").projectDir = File("../../phone")
include(":persist")
project(":persist").projectDir = File("../../persist")
include(":http")
project(":http").projectDir = File("../../http")
include(":sms-gateway-core")
project(":sms-gateway-core").projectDir = File("../sms-gateway-core")