pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../../build-src")
includeBuild("../../test")

include(":theme-core")
project(":theme-core").projectDir = File("../../theme/theme-core")

include(":theme-react")
project(":theme-react").projectDir = File("../../theme/theme-react")

include(":icons-react")
project(":icons-react").projectDir = File("../../icons/icons-react")

include(":tools-core")
project(":tools-core").projectDir = File("../../tools/tools-core")

include(":paging-core")
project(":paging-core").projectDir = File("../paging-core")

include(":tools-react")
project(":tools-react").projectDir = File("../../tools/tools-react")

include(":enterprise-react")
project(":enterprise-react").projectDir = File("../../enterprise/enterprise-react")