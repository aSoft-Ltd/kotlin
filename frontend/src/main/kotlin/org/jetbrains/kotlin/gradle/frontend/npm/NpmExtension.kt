package org.jetbrains.kotlin.gradle.frontend.npm

import org.gradle.api.Project
import org.gradle.api.tasks.InputFile
import org.jetbrains.kotlin.gradle.frontend.*
import org.jetbrains.kotlin.gradle.frontend.util.mkdirsOrFail
import java.io.File
import java.util.*

/**
 * @author Sergey Mashkov
 */
open class NpmExtension(val project: Project) {
    val dependencies: MutableList<Dependency> = ArrayList()

    val versionReplacements: MutableList<Dependency> = ArrayList()

    val developmentDependencies: MutableList<Dependency> = ArrayList()

    @InputFile
    var nodeModulesDir: File =
        project.file(project.rootProject.buildDir.absolutePath + File.separator + "node_modules")
            .apply {
                mkdirsOrFail()
            }

    @JvmOverloads
    fun dependency(name: String, version: String = "*") {
        dependencies.add(Dependency(name, version, Dependency.RuntimeScope))
    }

    fun replaceVersion(name: String, version: String) {
        versionReplacements.add(Dependency(name, version, Dependency.RuntimeScope))
    }

    @JvmOverloads
    fun devDependency(name: String, version: String = "*") {
        developmentDependencies.add(Dependency(name, version, Dependency.DevelopmentScope))
    }

    fun webpackDependencies() {
        dependency("react", "^16.13.1")
        dependency("react-dom", "^16.13.1")
        dependency("styled-components", "^5.1.0")
        dependency("inline-style-prefixer", "^6.0.0")
        dependency("react-router-dom", "^5.1.2")
        dependency("text-encoding", "^0.7.0")

        dependency("core-js", "^3.0.0")

        dependency("firebase", "^7.14.1")
        dependency("react-responsive-carousel", "^3.2.7")
        dependency("react-event-timeline", "^1.5.4")

        dependency("echarts", "^4.7.0")
        dependency("simplebar-react", "^2.2.0")
        dependency("echarts-for-react", "^2.0.16")

        dependency("react-tabs", "^3.1.0")
        dependency("react-table", "6.10.3")
        dependency("react-draft-wysiwyg", "^1.14.5")
        dependency("draft-js", "0.11.5")
        dependency("draft-js-export-html", "^1.4.1")
        dependency("react-icons", "^3.10.0")
        dependency("abort-controller", "^3.0.0")

        dependency("@react-google-maps/api", "^1.9.2")

        devDependency("file-loader", "^6.0.0")
        devDependency("style-loader", "^1.2.1")
        devDependency("css-loader", "^3.5.3")
        devDependency("less", "^3.11.1")
        devDependency("less-loader", "^6.1.0")
        devDependency("babel-loader", "^8.1.0")

        devDependency("@babel/core", "^7.9.6")
        devDependency("@babel/preset-env", "^7.9.6")
        devDependency("@babel/preset-react", "^7.9.4")

        devDependency("webpack", "^4.43.0")
        devDependency("webpack-cli", "^3.3.11")
        devDependency("webpack-dev-server", "^3.11.0")
    }
}