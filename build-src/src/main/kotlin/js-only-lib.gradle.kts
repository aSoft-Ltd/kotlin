plugins {
    kotlin("js")
    id("space-maven")
}

kotlin {
    target {
        useCommonJs()
    }
}

publishing.publications {
    create<MavenPublication>("kotlin") {
        from(components["kotlin"])
        groupId = project.group.toString()
        artifactId = project.name
        version = project.version.toString()
        artifact(tasks.getByName<Zip>("JsSourcesJar"))
    }
}