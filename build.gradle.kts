allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
    group = "tz.co.asoft"
    version = "0.1.0-dev-04"
}

listOf("primary", "secondary", "tertiary", "gradle-plugins", "ui-libs").forEach { group ->
    project.tasks.create("upload-$group") {
        project.file(group).listFiles().orEmpty().forEach { project ->
            dependsOn(":$group:${project.name}:publish")
        }
    }
}