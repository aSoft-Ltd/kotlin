package tz.co.asoft.deployment.tasks

import groovy.json.JsonBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectories
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget
import tz.co.asoft.deployment.target.Deployment
import tz.co.asoft.deployment.tools.getOrCreateNewFile
import tz.co.asoft.deployment.tools.resolveDir
import java.io.File
import java.io.Serializable
import javax.inject.Inject

open class EnvironmentJsonFileTask @Inject constructor(
    @Input val target: Target,
    @Input val deployment: Deployment
) : DefaultTask() {

    @OutputDirectories
    val outputDirs = mutableListOf<File>().apply {
        val kt = target
        if (kt is Target.AndroidTarget) {
            add(project.resolveDir("build/intermediates/merged_assets/${deployment.name}/out"))
        }

        if (kt is Target.JvmTarget) {
            add(project.resolveDir("build/processedResources/${kt.name}/main"))
        }

        if (kt is Target.JsTarget) {
            add(project.resolveDir("build/resources/main"))
        }
    }

    @OutputFiles
    val outputFiles = outputDirs.map { it.getOrCreateNewFile("platform.environment.json") }

    @TaskAction
    fun doAction() {
        outputFiles.forEach { it.writeText(JsonBuilder(deployment.values).toPrettyString()) }
    }
}

sealed class Target(val name: String) : Serializable {
    class AndroidTarget(name: String) : Target(name)
    class JvmTarget(name: String) : Target(name)
    class JsTarget(name: String) : Target(name)
    class UnsuportedTarget(name: String) : Target(name)
}

val KotlinTarget.toTarget: Target
    get() = when (this) {
        is KotlinAndroidTarget -> Target.AndroidTarget(name)
        is KotlinJvmTarget -> Target.JvmTarget(name)
        is KotlinJsTarget -> Target.JsTarget(name)
        else -> Target.UnsuportedTarget(name)
    }
