pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":viewmodel-core")
project(":viewmodel-core").projectDir = File("../viewmodel-core")
include(":tools-core")
project(":tools-core").projectDir = File("../../tools/tools-core")
include(":tools-react")
project(":tools-react").projectDir = File("../../tools/tools-react")

