import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.internal.artifact.FileBasedMavenArtifact
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import java.util.*

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

fun BintrayExtension.configureBintray(project: Project) {
    val props = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }
    user = props["bintray.user"] as String
    key = props["bintray.key"] as String
    project.addModuleJson()
    publish = true
    setPublications("kotlinMultiplatform", "metadata", "androidRelease", "jvm", "js")
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