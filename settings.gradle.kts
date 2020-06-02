rootProject.name = "asoft-kotlin-libs"

listOf("primary", "secondary", "tertiary", "ui-libs", "gradle-plugins").forEach { group ->
    rootProject.projectDir.resolve(group).listFiles()?.forEach { dir ->
        include(":$group:${dir.name}")
    }
}
