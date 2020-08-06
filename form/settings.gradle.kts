pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")
include(":tools-core")
project(":tools-core").projectDir = File("../tools/tools-core")
include(":theme-core")
project(":theme-core").projectDir = File("../theme/theme-core")
include(":theme-react")
project(":theme-react").projectDir = File("../theme/theme-react")

include(":react-core")
project(":react-core").projectDir = File("../react/react-core")

include(":form-core")
include(":form-react")
