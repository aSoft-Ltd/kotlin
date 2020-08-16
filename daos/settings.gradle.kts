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
include(":tools")
project(":tools").projectDir = File("../tools")
include(":persist")
project(":persist").projectDir = File("../persist")
include(":paging-core")
project(":paging-core").projectDir = File("../paging/paging-core")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":io")
project(":io").projectDir = File("../io")
include(":form-http")
project(":form-http").projectDir = File("../form/form-http")
include(":result")
project(":result").projectDir = File("../result")

include(":firestore-dao")
include(":neo4j-dao")
include(":rest-dao")