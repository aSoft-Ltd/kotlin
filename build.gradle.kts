allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
    group = "tz.co.asoft"
    version = "0.1.0-dev-07"
}

val groups = listOf("primary", "secondary", "tertiary", "gradle-plugins", "ui-libs")

val sensitives = listOf("camera", "ui", "test")

groups.forEach { group ->
    project.tasks.create("upload-$group") {
        project.file(group).listFiles().orEmpty().filter {
            !sensitives.contains(it.name)
        }.forEach { project ->
            dependsOn(":$group:${project.name}:publish")
        }
    }
}

subprojects {
    if (!groups.contains(name)) {
        afterEvaluate {
            val jarTasks = tasks.filter {
                it.name.contains("jar", true)
            }.mapNotNull {
                if (it.name == "jvmJar" && sensitives.contains(it.project.name)) {
                    null
                } else {
                    it
                }
            }
            tasks.create("sensitive-build") {
                dependsOn(jarTasks)
            }
        }
    }
}

tasks.create("publish-sensitives") {
    dependsOn(":primary:test:publish")
    dependsOn(":secondary:ui:publish")
    dependsOn(":tertiary:camera:publish")
}