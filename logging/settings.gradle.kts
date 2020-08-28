pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")
include(":logging-core")
include(":samples:browser")
//include(":klock")
//project(":klock").projectDir = File("../klock")
//include(":persist")
//project(":persist").projectDir = File("../persist")
//include(":paging-core")
//project(":paging-core").projectDir = File("../paging/paging-core")