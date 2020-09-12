package tz.co.asoft.deployment.tools

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget
import tz.co.asoft.deployment.extensions.DeploymentExtension

val Project.isPureAndroid get() = plugins.hasPlugin("org.jetbrains.kotlin.android")
val Project.androidTarget get() = (project.extensions.getByName("kotlin") as KotlinAndroidProjectExtension).target

val Project.kotlinExt get() = extensions.findByType(KotlinMultiplatformExtension::class.java)

val Project.targetsAndroid: Boolean get() = kotlinExt?.targets?.findByName("android") != null

val Project.targetsJs: Boolean get() = kotlinExt?.targets?.findByName("js") != null

val Project.targetsJvm: Boolean get() = kotlinExt?.targets?.findByName("jvm") != null

val Project.extension get() = extensions.findByType(DeploymentExtension::class.java)

var KotlinJvmTarget.mainClassName: String
    set(value) {
        project.extension?.targetMainClassNames?.put(this, value)
    }
    get() = project.extension?.targetMainClassNames?.get(this) ?: throw Exception("Main Class Name for target ${this.name} isn't set")