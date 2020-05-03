import org.gradle.api.Project

val Project.gitPath get() = "${projectDir.parentFile.name}/${projectDir.name}"