pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
include(":tools-core")
include(":tools-react")
