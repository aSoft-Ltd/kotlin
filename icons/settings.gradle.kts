pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
include("icons-react:icons-react-core")
listOf("ai", "bs", "di", "fa", "fc", "fi", "gi", "go", "gr", "io", "md", "ri", "ti", "wi").forEach {
    include(":icons-react:icons-react-$it")
}
