pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")

include(":tools-core")
project(":tools-core").projectDir = File("../tools/tools-core")
include(":tools-react")
project(":tools-react").projectDir = File("../tools/tools-react")

include(":viewmodel-core")
include(":viewmodel-react")
