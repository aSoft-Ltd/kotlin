pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")

include(":form-http")
project(":form-http").projectDir = File("../form/form-http")
include(":io")
project(":io").projectDir = File("../io")
include(":klock")
project(":klock").projectDir = File("../klock")

//include(":persist")
//project(":persist").projectDir = File("../persist")
//include(":paging-core")
//project(":paging-core").projectDir = File("../paging/paging-core")

include(":logging-core")
include(":logging-console")
include(":logging-file")
include(":logging-rest")

//include(":samples:browser")
