pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")
include(":tools")
project(":tools").projectDir = File("../tools")
include(":io")
project(":io").projectDir = File("../io")
include(":klock")
project(":klock").projectDir = File("../klock")
include(":theme-core")
project(":theme-core").projectDir = File("../theme/theme-core")
include(":theme-react")
project(":theme-react").projectDir = File("../theme/theme-react")

include(":react-core")
project(":react-core").projectDir = File("../react/react-core")

include(":form-http")
include(":form-html")
include(":form-react")
