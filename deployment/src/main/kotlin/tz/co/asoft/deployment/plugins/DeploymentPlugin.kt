package tz.co.asoft.deployment.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import tz.co.asoft.deployment.extensions.DeploymentExtension

open class DeploymentPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("deployment", DeploymentExtension::class.java, project)
        project.afterEvaluate { it.createEnvironmentTask() }
    }

    private fun Project.createEnvironmentTask() {
        val extension = extensions.getByType(DeploymentExtension::class.java)
        tasks.create("targets").apply {
            doFirst {
                println("= = = = = = Deployment Targets = = = = = =")
                extension.deployments.forEach { println(it) }
                println("Total: ${extension.deployments.size} Targets")
            }
        }
    }
}