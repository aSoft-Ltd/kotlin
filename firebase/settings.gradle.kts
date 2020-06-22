pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")

include(":firebase-core")
include(":firebase-storage")
include(":firebase-firestore")
include(":firebase-auth")
include(":firebase-daos")

include(":rx")
project(":rx").projectDir = File("../rx")
include(":persist")
project(":persist").projectDir = File("../persist")
include(":platform")
project(":platform").projectDir = File("../platform")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":io")
project(":io").projectDir = File("../io")
include(":phone")
project(":phone").projectDir = File("../phone")
include(":email")
project(":email").projectDir = File("../email")
include(":krypto")
project(":krypto").projectDir = File("../krypto")
include(":neo4j")
project(":neo4j").projectDir = File("../neo4j")
include(":storage")
project(":storage").projectDir = File("../storage")
include(":auth")
project(":auth").projectDir = File("../auth")