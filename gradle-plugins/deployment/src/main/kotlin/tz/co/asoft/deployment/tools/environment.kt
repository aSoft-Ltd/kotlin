package tz.co.asoft.deployment.tools

import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import tz.co.asoft.deployment.target.Deployment

fun Deployment.envTaskName(target: KotlinTarget): String {
    return "environmentJson${nameEnd(target)}"
}

fun Deployment.nameEnd(target: KotlinTarget) = "${target.name.capitalize()}${name.capitalize()}"