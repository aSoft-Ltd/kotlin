package tz.co.asoft.environment.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class EnvironmentsTask extends DefaultTask {
    @Input
    Map<String, Object> envs = [:]

    @TaskAction
    void run() {
        println("${envs.size()} environments configured")
        envs.each { k, v -> println(PrepareEnvTask.toJson(v)) }
    }
}
