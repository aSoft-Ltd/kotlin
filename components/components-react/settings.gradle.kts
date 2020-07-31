pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
include(":viewmodel-core")
project(":viewmodel-core").projectDir = File("../../viewmodel/viewmodel-core")
include(":viewmodel-react")
project(":viewmodel-react").projectDir = File("../../viewmodel/viewmodel-react")
include(":tools-react")
project(":tools-react").projectDir = File("../../tools/tools-react")
include(":tools-core")
project(":tools-core").projectDir = File("../../tools/tools-core")
