pluginManagement {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

includeBuild("../build-src")
includeBuild("../frontend")
include(":konfig-plugin")
include(":konfig")
