pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":theme-core")
project(":theme-core").projectDir = File("../theme-core")
include(":tools-react")
project(":tools-react").projectDir = File("../../tools/tools-react")
