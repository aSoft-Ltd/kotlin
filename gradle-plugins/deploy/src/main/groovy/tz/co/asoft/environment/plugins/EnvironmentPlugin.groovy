package tz.co.asoft.environment.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Exec
import tz.co.asoft.environment.extensions.EnvironmentPluginExtension
import tz.co.asoft.environment.tasks.EnvironmentsTask
import tz.co.asoft.environment.tasks.PrepareEnvTask

class EnvironmentPlugin implements Plugin<Project> {
    static def createPrepareEnvTasks(Project project, List<File> files, Task anchorTask, Map<String, Map<String, Object>> buildTypes) {
        buildTypes.each { k, v ->
            def taskName = "prepare${k.capitalize()}Env"
            project.task(taskName, type: PrepareEnvTask) {
                finalizedBy anchorTask
                group = "environment"
                outputDirs = files
                envs = v
            }
        }
    }

    static def createJsTasks(Project project, Map<String, Map<String, Object>> envs) {
        envs.each { k, v ->
            def prepareTask = "prepare${k.capitalize()}Env"
            project.task("run${k.capitalize()}") {
                group = "run"
                dependsOn project.tasks.getByName(prepareTask)
                finalizedBy project.tasks.getByName("run")
            }

            project.task("bundle${k.capitalize()}") {
                group = "build"
                dependsOn project.tasks.getByName(prepareTask)
                finalizedBy project.tasks.getByName("bundle")
            }
        }
    }

    static def createJvmTasks(Project project, Map<String, Map<String, Object>> envs) {
        envs.each { k, v ->
            def prepareTask = project.tasks.getByName("prepare${k.capitalize()}Env")
            project.task("run${k.capitalize()}") {
                group = "run"
                dependsOn prepareTask
                finalizedBy project.tasks.getByName("run")
            }

            project.task("installDist${k.capitalize()}") {
                group = "distribution"
                dependsOn prepareTask

                doLast {
                    project.copy {
                        from("build/install/${project.name}")
                        into(("build/install/${project.name}-$k"))
                    }
                }
            }
        }
    }

    static def createAndroidTasks(Project project, Map<String, Map<String, Object>> envs) {
        project.android.applicationVariants.all { variant ->
            def prepareTask = project.tasks.getByName("prepare${variant.name.capitalize()}Env")
            project.task("installRun${variant.name.capitalize()}", type: Exec) {
                group = "run"
                dependsOn prepareTask
                dependsOn "install${variant.name.capitalize()}"
                commandLine = ["adb", "shell", "monkey", "-p", variant.applicationId + " 1"]
                doLast {
                    println "Launching ${variant.applicationId}"
                }
            }

            project.tasks.getByName("assemble${variant.name.capitalize()}").dependsOn(prepareTask)
        }
    }

    @Override
    void apply(Project project) {
        EnvironmentPluginExtension environments = project.extensions.create("environments", EnvironmentPluginExtension)
        Map<String, Map<String, Object>> buildTypes = environments.environments
        project.afterEvaluate {
            List<File> files = []
            try {
                def task = project.tasks.getByName("compileKotlinJs")
                println("${task.name} found, creating environment tasks for JS Environment")
                files += project.file("build/resources/main")
                createPrepareEnvTasks(project, files, task, buildTypes)
                createJsTasks(project, buildTypes)
            } catch (Exception e) {

            }

            try {
                def task = project.tasks.getByName("installDist")
                println("${task.name} found, creating environment tasks for JVM Environment")
                files += project.file("build/resources/main")
                createPrepareEnvTasks(project, files, task, buildTypes)
                createJvmTasks(project, buildTypes)
            } catch (Exception e) {

            }

            try {
                def task = project.tasks.getByName("preBuild")
                println("${task.name} found, creating environment tasks for Android Environment")
                buildTypes.each { k, v ->
                    def fileList = [project.file("build/intermediates/merged_assets/$k/merge${k.capitalize()}Assets/out")]
                    createPrepareEnvTasks(project, fileList, task, [(k): v])
                }
                createAndroidTasks(project, buildTypes)
            } catch (Exception e) {
                println(e)
            }
            project.task("environments", type: EnvironmentsTask) {
                group = "environment"
                envs = buildTypes
            }
        }
    }
}