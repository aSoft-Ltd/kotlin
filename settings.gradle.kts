rootProject.name = "asoft-kotlin-libs"

listOf("primary", "secondary", "tertiary").forEach { group ->
    rootProject.projectDir.resolve(group).listFiles()?.forEach { dir ->
        include(":$group:${dir.name}")
    }
}