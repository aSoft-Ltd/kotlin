package tz.co.asoft.deployment.extensions

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.frontend.FrontendPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget
import tz.co.asoft.deployment.target.Deployment
import tz.co.asoft.deployment.target.TargetDeligate
import tz.co.asoft.deployment.tasks.EnvironmentJsonFileTask
import tz.co.asoft.deployment.tasks.toTarget
import tz.co.asoft.deployment.tools.*

open class DeploymentExtension(val project: Project) {
    val deployments = mutableListOf<Deployment>()

    fun creating(vararg values: Pair<String, Any>) = TargetDeligate(values.toMap().toMutableMap())

    fun deploy(vararg deployments: Deployment) {
        this.deployments.addAll(deployments)
        project.kotlinExt?.targets?.forEach { createTasks(it, deployments) }
    }

    private fun createTasks(target: KotlinTarget, deployments: Array<out Deployment>) {
        deployments.forEach { deployment ->
            project.tasks.create(
                deployment.envTaskName(target),
                EnvironmentJsonFileTask::class.java,
                target.toTarget,
                deployment
            ).apply {
                group = "environment"
            }

            when (target) {
                is KotlinAndroidTarget -> project.afterEvaluate {
                    createTasksForAndroid(target, deployment)
                }
                is KotlinJvmTarget -> createTasksForJvm(target, deployment)
                is KotlinJsTarget -> createTasksForJs(target, deployment)
            }
        }
    }

    private fun createTasksForAndroid(target: KotlinAndroidTarget, deployment: Deployment) {
        val androidExt = project.extensions.findByType(BaseAppModuleExtension::class.java)
        val variants = androidExt?.applicationVariants
        val variant = variants?.firstOrNull {
            it.name.toLowerCase() == deployment.name.toLowerCase()
        } ?: return

        val ne = deployment.nameEnd(target)
        val env = deployment.envTaskName(target)

        project.tasks.getByName("assemble${variant.name.capitalize()}").apply {
            group = "assemble"
            dependsOn(env)
        }

        val install = project.tasks.getByName("install${variant.name.capitalize()}").apply {
            group = "install"
            dependsOn(env)
        }

        project.tasks.create("installRun$ne", Exec::class.java).apply {
            group = "run"
            dependsOn(env)
            dependsOn(install)
            commandLine("adb", "shell", "monkey", "-p", variant.applicationId + " 1")
            doLast { println("Launching ${variant.applicationId}") }
        }
    }

    private fun createTasksForJvm(target: KotlinJvmTarget, deployment: Deployment) {
        val jar = project.tasks.findByName("${target.name}Jar") as? Jar ?: return

        val ne = deployment.nameEnd(target)
        val env = project.tasks.getByName(deployment.envTaskName(target))

        val jarName = target.name + "-" + deployment.name

        val fatJar = project.tasks.create("fatJar$ne", Jar::class.java) {
            it.group = "assemble"
            it.archiveBaseName.value(jarName)
            it.archiveVersion.value(project.version.toString())
            it.dependsOn(env)
            it.with(jar)
            it.doFirst { _ ->
                it.manifest { it.attributes["Main-Class"] = deployment.values["Main-Class"] }
                val compile = project.configurations.getByName("${target.name}RuntimeClasspath")
                it.from(compile.map { if (it.isDirectory) it else project.zipTree(it) })
            }
        }

        project.tasks.create("run$ne", Exec::class.java) {
            it.group = "run"
            it.dependsOn(env)
            it.dependsOn(fatJar)
            it.workingDir("build/libs")
            it.commandLine("java", "-jar", "$jarName-${project.version}.jar")
        }
    }

    private fun createTasksForJs(target: KotlinJsTarget, deployment: Deployment) {
        if (!project.plugins.hasPlugin(FrontendPlugin::class.java)) {
            project.plugins.apply(FrontendPlugin::class.java)
        }

        if (!project.plugins.hasPlugin("kotlin-dce-js")) {
            project.plugins.apply("kotlin-dce-js")
        }

        val ne = deployment.nameEnd(target)
        val env = project.tasks.getByName(deployment.envTaskName(target))

        project.tasks.create("run$ne").apply {
            group = "run"
            dependsOn(env)
            finalizedBy(project.tasks.getByName("run"))
        }

        val bundleTask = project.tasks.create("bundle$ne").apply {
            group = "bundle"
            dependsOn(env)
            finalizedBy(project.tasks.getByName("bundle"))
        }

        project.tasks.create("deploy$ne").apply {
            group = "deployment"
            dependsOn(bundleTask)
            doLast {
                val output = "build/deployment/${target.name}/${deployment.name}"
                val deployDir = project.resolveDir(output)
                val mainSourceSets = project.kotlinExt?.sourceSets?.findByName("${target.name}Main")
                val resources = mainSourceSets?.resources
                val resDirs = resources?.sourceDirectories?.filterNotNull() ?: listOf()
                project.copy {
                    resDirs.forEach { dir -> it.from(dir) }
                    it.from("build/bundle", "src/${target.name}Main/resources")
                    it.into(deployDir)
                }
                println("Copied into ${deployDir.absolutePath}")
            }
        }
    }
}