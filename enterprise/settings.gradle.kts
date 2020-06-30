pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
include(":theme")
project(":theme").projectDir = File("../theme")

include(":tools")
project(":tools").projectDir = File("../tools")

include(":components")
project(":components").projectDir = File("../components")