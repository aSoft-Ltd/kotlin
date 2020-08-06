pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")

include(":viewmodel-core")
project(":viewmodel-core").projectDir = File("../viewmodel/viewmodel-core")
include(":viewmodel-react")
project(":viewmodel-react").projectDir = File("../viewmodel/viewmodel-react")
include(":tools")
project(":tools").projectDir = File("../tools")
include(":react-core")
project(":react-core").projectDir = File("../react/react-core")

include(":components-react")