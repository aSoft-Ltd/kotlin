pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":neo4j")
project(":neo4j").projectDir = File("../../neo4j")
include(":result")
project(":result").projectDir = File("../../result")
include(":persist")
project(":persist").projectDir = File("../../persist")
include(":paging-core")
project(":paging-core").projectDir = File("../../paging/paging-core")
include(":klock")
project(":klock").projectDir = File("../../klock")
include(":io")
project(":io").projectDir = File("../../io")
include(":http")
project(":http").projectDir = File("../../http")
include(":rest-dao")
project(":rest-dao").projectDir = File("../../rest-dao")
include(":rest-controller-core")
project(":rest-controller-core").projectDir = File("../rest-controller-core")
