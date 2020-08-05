pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
includeBuild("../test")

include(":theme-core")
project(":theme-core").projectDir = File("../theme/theme-core")

include(":theme-react")
project(":theme-react").projectDir = File("../theme/theme-react")

include(":icons-react-core")
project(":icons-react-core").projectDir = File("../icons/icons-react/icons-react-core")

include(":tools-core")
project(":tools-core").projectDir = File("../tools/tools-core")

include(":tools-react")
project(":tools-react").projectDir = File("../tools/tools-react")

include(":enterprise-react")
