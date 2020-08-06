pluginManagement {
    repositories {
        google()
        jcenter()
    }
}

includeBuild("../build-src")
listOf("core", "ai", "bs", "di", "fa", "fc", "fi", "gi", "go", "gr", "io", "md", "ri", "ti", "wi").forEach {
    include(":icons-react-$it")
    project(":icons-react-$it").projectDir = File("icons-react/icons-react-$it")
}
