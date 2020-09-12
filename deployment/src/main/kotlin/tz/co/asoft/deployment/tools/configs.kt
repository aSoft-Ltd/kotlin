package tz.co.asoft.deployment.tools

import org.jetbrains.kotlin.gradle.plugin.KotlinTarget

private val individualDeployments: MutableMap<KotlinTarget, Map<String, Any>> = mutableMapOf()

fun KotlinTarget.configs(vararg pairs: Pair<String, Any>) {
    individualDeployments[this] = mapOf(*pairs)
}

val KotlinTarget.configs get() = individualDeployments[this] ?: mapOf()