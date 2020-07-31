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
include(":http")
project(":http").projectDir = File("../../http")
include(":persist")
project(":persist").projectDir = File("../../persist")
include(":paging-core")
project(":paging-core").projectDir = File("../../paging/paging-core")
include(":result")
project(":result").projectDir = File("../../result")