pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
includeBuild("../../test")
include(":tools-core")
project(":tools-core").projectDir = File("../../tools/tools-core")
include(":result")
project(":result").projectDir = File("../../result")
include(":persist")
project(":persist").projectDir = File("../../persist")
include(":paging-core")
project(":paging-core").projectDir = File("../../paging/paging-core")
include(":rest-controller-core")
project(":rest-controller-core").projectDir = File("../rest-controller-core")
