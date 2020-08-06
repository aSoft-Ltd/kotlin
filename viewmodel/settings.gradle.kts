pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")

include(":tools")
project(":tools").projectDir = File("../tools")

include(":react-core")
project(":react-core").projectDir = File("../react/react-core")

include(":viewmodel-core")
include(":viewmodel-react")
