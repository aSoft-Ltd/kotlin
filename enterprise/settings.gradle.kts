pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")

include(":theme")
project(":theme").projectDir = File("../theme")

include(":icons")
project(":icons").projectDir = File("../icons")

include(":tools")
project(":tools").projectDir = File("../tools")