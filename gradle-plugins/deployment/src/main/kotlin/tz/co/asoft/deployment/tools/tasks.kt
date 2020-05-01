package tz.co.asoft.deployment.tools

import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer

inline fun <reified T : Task> TaskContainer.getOrCreate(name: String): T {
    return findByName(name) as? T ?: create(name, T::class.java)
}