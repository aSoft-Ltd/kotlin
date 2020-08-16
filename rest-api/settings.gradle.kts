pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")
include(":tools")
project(":tools").projectDir = File("../tools")
include(":logging")
project(":logging").projectDir = File("../logging")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":result")
project(":result").projectDir = File("../result")
include(":persist")
project(":persist").projectDir = File("../persist")
include(":paging-core")
project(":paging-core").projectDir = File("../paging/paging-core")

include(":rest-api-core")
include(":rest-api-ktor")
