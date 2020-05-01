import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.gradle.api.publish.maven.internal.artifact.FileBasedMavenArtifact
import java.util.*

plugins {
    id("maven-publish")
    id("com.jfrog.bintray")
}

bintray {
    val props = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }
    user = props["bintray.user"] as String
    key = props["bintray.key"] as String
    project.addModuleJson()
    publish = true
    setPublications(*publishing.publications.map { it.name }.toTypedArray())
    pkg = PackageConfig().apply {
        repo = "kotlin"
        name = project.name
        userOrg = "asofttz"
        setLicenses("WTFPL")
        vcsUrl = "https://github.com/andylamax/${project.name}.git"
        version = VersionConfig().apply {
            name = project.version as String
            vcsTag = name
        }
    }
}

fun Project.addModuleJson() = tasks.withType<BintrayUploadTask> {
    doFirst {
        project.extensions.getByType<PublishingExtension>().publications
            .filterIsInstance<MavenPublication>()
            .forEach { publication ->
                val moduleFile = buildDir.resolve("publications/${publication.name}/module.json")
                if (moduleFile.exists()) {
                    publication.artifact(object : FileBasedMavenArtifact(moduleFile) {
                        override fun getDefaultExtension() = "module"
                    })
                }
            }
    }
}