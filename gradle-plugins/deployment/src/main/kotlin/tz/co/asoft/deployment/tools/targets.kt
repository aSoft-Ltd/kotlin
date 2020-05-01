package tz.co.asoft.deployment.tools

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

val Project.kotlinExt get() = extensions.findByType(KotlinMultiplatformExtension::class.java)

val Project.targetsAndroid: Boolean get() = kotlinExt?.targets?.findByName("android") != null

val Project.targetsJs: Boolean get() = kotlinExt?.targets?.findByName("js") != null

val Project.targetsJvm: Boolean get() = kotlinExt?.targets?.findByName("jvm") != null