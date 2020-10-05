pluginManagement {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

includeBuild("../build-src")
includeBuild("../frontend")

include(":mapper")
project(":mapper").projectDir = File("../mapper")

include(":konfig-plugin")
include(":konfig")
