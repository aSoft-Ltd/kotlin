allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
    group = "tz.co.asoft"
    version = "0.1.0-dev-05"
}

val groups = listOf("primary", "secondary", "tertiary", "gradle-plugins", "ui-libs")

groups.forEach { group ->
    project.tasks.create("upload-$group") {
        project.file(group).listFiles().orEmpty().forEach { project ->
            dependsOn(":$group:${project.name}:publish")
        }
    }
}

subprojects {
    if (name != "ui" && !groups.contains(name)) {
        afterEvaluate {
            val jarTasks = tasks.filter { it.name.contains("jar", true) }
            tasks.create("sensitive-build") {
                dependsOn(jarTasks)
            }
        }
    }
}