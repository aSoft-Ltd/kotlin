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

include(":icons-react-fa")
project(":icons-react-fa").projectDir = File("../icons/icons-react/icons-react-fa")

include(":tools")
project(":tools").projectDir = File("../tools")

include(":react-core")
include(":react-buttons")
include(":react-tabs")
include(":react-text")
include(":react-media")
include(":react-feedback")
include(":react-inputs")
include(":react-navigations")
include(":react-layouts")
include(":react-tables")
include(":react-webcomposites")
include(":react-webpages")