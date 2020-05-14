allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
    group = "tz.co.asoft"
    version = "0.0.14"
}

listOf("primary", "secondary", "tertiary", "gradle-plugins").forEach { group ->
    project.tasks.create("upload-$group") {
        project.file(group).listFiles().orEmpty().forEach { project ->
            dependsOn(":$group:${project.name}:bintrayUpload")
        }
    }
}