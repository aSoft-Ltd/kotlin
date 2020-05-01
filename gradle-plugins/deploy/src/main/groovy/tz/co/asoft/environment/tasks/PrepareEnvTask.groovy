package tz.co.asoft.environment.tasks

import groovy.json.JsonBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectories
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.TaskAction

class PrepareEnvTask extends DefaultTask {
    @OutputDirectories
    List<File> outputDirs = [project.buildDir];

    @Input
    Map<String, Object> envs = [:]

    @OutputFiles
    List<File> getOutputFiles() {
        List<File> files = []
        outputDirs.each {
            if (!it.exists()) {
                it.mkdirs()
            }
            File f = new File("${it.path}/platform.environment.json")
            if (!f.exists()) {
                f.createNewFile()
            }
            files += f
        }
        return files
    }

    static String toJson(Map<String, Object> map) {
        return new JsonBuilder(map).toPrettyString()
    }

    @TaskAction
    void prepare() {
        outputFiles.each {
            println("Writing to $it.path")
            it.write(toJson(envs))
        }
    }
}