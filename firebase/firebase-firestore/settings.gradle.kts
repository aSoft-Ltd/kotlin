pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
includeBuild("../../test")

include(":firebase-core")
project(":firebase-core").projectDir = File("../firebase-core")
include(":tools-core")
project(":tools-core").projectDir = File("../../tools/tools-core")