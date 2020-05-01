package tz.co.asoft.deployment.tasks

import groovy.json.JsonBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import tz.co.asoft.deployment.target.Target
import tz.co.asoft.deployment.tools.*
import java.io.File
import javax.inject.Inject

open class EnvironmentJsonFileTask @Inject constructor(@Input val target: Target) : DefaultTask() {
    @OutputDirectories
    val outputDirs = mutableListOf<File>().apply {
        if (project.targetsAndroid) {
            val k = target.name
            add(project.resolveDir("build/intermediates/merged_assets/$k/merge${k.capitalize()}Assets/out"))
        }

        if (project.targetsJvm) {
            add(project.resolveDir("build/processedResources/jvm/main"))
        }

        if (project.targetsJs) {
            add(project.resolveDir("build/resources/main"))
        }
    }

    @OutputFiles
    val outputFiles = outputDirs.map { it.getOrCreateNewFile("platform.environment.json") }

    @TaskAction
    fun doAction() {
        outputFiles.forEach { it.writeText(JsonBuilder(target.values).toPrettyString()) }
    }
}