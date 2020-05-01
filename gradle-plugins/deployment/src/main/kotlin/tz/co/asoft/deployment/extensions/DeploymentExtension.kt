package tz.co.asoft.deployment.extensions

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.frontend.FrontendPlugin
import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget
import tz.co.asoft.deployment.target.Target
import tz.co.asoft.deployment.target.TargetDeligate
import tz.co.asoft.deployment.tasks.EnvironmentJsonFileTask
import tz.co.asoft.deployment.tools.*

open class DeploymentExtension(val project: Project) {
    val targets = mutableListOf<Target>()

    fun creating(vararg values: Pair<String, Any>) = TargetDeligate(values.toMap().toMutableMap())

    fun deploy(vararg targets: Target) {
        this.targets.addAll(targets)
        createTasks(targets)
    }

    private fun createTasks(targets: Array<out Target>) = targets.forEach {
        project.tasks.create(it.envTaskName, EnvironmentJsonFileTask::class.java, it)
        when {
            project.targetsAndroid -> createTasksForAndroid(it)
            project.targetsJvm -> createTasksForJvm(it)
            project.targetsJs -> createTasksForJs(it)
        }
    }

    private fun createTasksForAndroid(target: Target) {
        val variants = project.extensions.findByType(AppExtension::class.java)?.applicationVariants
        val variant = variants?.firstOrNull { it.name == target.name } ?: return

        val installRunTaskName = "installRun${target.name.capitalize()}"

        project.tasks.create(installRunTaskName, Exec::class.java) {
            it.group = "run"
            it.dependsOn(project.tasks.getByName(target.envTaskName))
            it.dependsOn("installRun${target.name.capitalize()}")
            it.commandLine("adb", "shell", "monkey", "-p", variant.applicationId + " 1")
            it.doLast { println("Launching ${variant.applicationId}") }
        }

        project.tasks.create("run${target.name.capitalize()}").dependsOn(installRunTaskName)

        project.tasks.getByName("assemble${target.name.capitalize()}").dependsOn(target.envTaskName)
    }

    private fun createTasksForJvm(target: Target) {
        val jvmJar = project.tasks.findByName("jvmJar") as? Jar ?: return

        project.tasks.create("fatJar${target.name.capitalize()}", Jar::class.java).apply {
            archiveBaseName.value(target.name)
            archiveVersion.value(project.version.toString())
            with(jvmJar)
            dependsOn(target.envTaskName)
            doFirst {
                manifest { it.attributes["Main-Class"] = target.values["Main-Class"] }
                val compile = project.configurations.getByName("jvmRuntimeClasspath")
                from(compile.map { if (it.isDirectory) it else project.zipTree(it) })
            }
        }

        project.tasks.create("run${target.name.capitalize()}", Exec::class.java) {
            it.dependsOn("fatJar${target.name.capitalize()}")
            it.workingDir("build/libs")
            it.commandLine("java", "-jar", "${target.name}-${project.version}.jar")
        }
    }

    private fun createTasksForJs(target: Target) {
        project.plugins.apply(FrontendPlugin::class.java)

        project.tasks.create("run${target.name.capitalize()}").apply {
            group = "run"
            dependsOn(project.tasks.getByName(target.envTaskName))
            finalizedBy(project.tasks.getByName("run"))
        }

        val bundleTask = project.tasks.create("bundle${target.name.capitalize()}").apply {
            group = "bundle"
            dependsOn(project.tasks.getByName(target.envTaskName))
            finalizedBy(project.tasks.getByName("bundle"))
            doLast {
                val deployDir = project.resolveDir("build/deployment/${target.name}")
                project.copy {
                    it.from("build/bundle", "src/jsMain/resources", "build/resources/main")
                    it.into(deployDir)
                }
            }
        }

        project.tasks.create("deploy${target.name.capitalize()}").apply {
            group = "deployment"
            dependsOn(bundleTask)
        }
    }
}