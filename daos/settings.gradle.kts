pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")

include(":firebase-core")
project(":firebase-core").projectDir = File("../firebase/firebase-core")
include(":firebase-firestore")
project(":firebase-firestore").projectDir = File("../firebase/firebase-firestore")
include(":tools-core")
project(":tools-core").projectDir = File("../tools/tools-core")
include(":persist")
project(":persist").projectDir = File("../persist")
include(":paging-core")
project(":paging-core").projectDir = File("../paging/paging-core")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":io")
project(":io").projectDir = File("../io")
include(":http")
project(":http").projectDir = File("../http")
include(":result")
project(":result").projectDir = File("../result")

include(":firestore-dao")
include(":neo4j-dao")
include(":rest-dao")