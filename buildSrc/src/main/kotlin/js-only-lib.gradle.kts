plugins {
    kotlin("js")
    id("bintray-upload-js")
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