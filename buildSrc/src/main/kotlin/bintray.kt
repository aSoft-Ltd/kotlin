import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.internal.artifact.FileBasedMavenArtifact
import org.gradle.kotlin.dsl.getByType
import java.util.*

fun BintrayExtension.configureBintray() {
    val props = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }
    user = (props["BINTRAY_USER"] as? String) ?: System.getenv("BINTRAY_USER")
    key = (props["BINTRAY_KEY"] as? String) ?: System.getenv("BINTRAY_KEY")
    project.addModuleJson()
    publish = true
    pkg = PackageConfig().apply {
        repo = "kotlin"
        name = project.name
        userOrg = "asofttz"
        setLicenses("WTFPL")
        vcsUrl = "https://github.com/aSoft-Ltd/kotlin/tree/master/${project.gitPath}"
        version = VersionConfig().apply {
            name = project.version as String
            vcsTag = name
        }
    }
}

private fun Project.addModuleJson() = tasks.withType(BintrayUploadTask::class.java) {
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