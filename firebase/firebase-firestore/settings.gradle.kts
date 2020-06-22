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
include(":persist")
project(":persist").projectDir = File("../../persist")
include(":platform")
project(":platform").projectDir = File("../../platform")
include(":neo4j")
project(":neo4j").projectDir = File("../../neo4j")