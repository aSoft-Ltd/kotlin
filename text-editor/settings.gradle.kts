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

include(":tools-core")
project(":tools-core").projectDir = File("../tools/tools-core")

include(":react-core")
project(":react-core").projectDir = File("../react/react-core")

include(":react-layouts")
project(":react-layouts").projectDir = File("../react/react-layouts")

include(":react-inputs")
project(":react-inputs").projectDir = File("../react/react-inputs")

include(":react-tabs")
project(":react-tabs").projectDir = File("../react/react-tabs")

include(":react-feedback")
project(":react-feedback").projectDir = File("../react/react-feedback")

include(":text-editor-core")
include(":text-editor-react")