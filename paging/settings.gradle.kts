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
project(":react-core").projectDir = File("../react/react-core")

include(":react-buttons")
project(":react-buttons").projectDir = File("../react/react-buttons")

include(":react-layouts")
project(":react-layouts").projectDir = File("../react/react-layouts")

include(":react-tables")
project(":react-tables").projectDir = File("../react/react-tables")

include(":react-feedback")
project(":react-feedback").projectDir = File("../react/react-feedback")

include(":paging-core")
include(":paging-react")
