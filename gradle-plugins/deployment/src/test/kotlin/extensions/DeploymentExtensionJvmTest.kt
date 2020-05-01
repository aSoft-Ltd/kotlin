package extensions

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import tools.makeParentsAndWriteText
import tz.co.asoft.deployment.extensions.DeploymentExtension
import tz.co.asoft.deployment.tools.resolveDir
import tz.co.asoft.deployment.tools.resolveFile
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DeploymentExtensionJvmTest {
    val projectDir = TemporaryFolder()
    val kotlinVersion = "1.3.72"
    val gradleVersion = "6.1.1"
    lateinit var runner: GradleRunner

    @BeforeTest
    fun setup() {
        projectDir.create()
        projectDir.root.resolveDir("build/kotlin-build/caches")

        projectDir.root.resolveFile("src/jvmMain/kotlin/index.kt").makeParentsAndWriteText(
            """
            fun main() {
                println("This works")
            }
        """.trimIndent()
        )

        projectDir.root.resolveFile("build.gradle.kts").makeParentsAndWriteText(
            """
            plugins{
                kotlin("multiplatform")
                id("tz.co.asoft.deployment")
            }
            
            version="0.0.1"
            
            repositories {
                jcenter()
                google()
                mavenCentral()
            }
            kotlin {
                jvm()
                sourceSets {
                    val jvmMain by getting {
                        dependencies {
                            implementation(kotlin("stdlib"))
                        }
                    }
                }
            }
            
            deployment {
                val common by creating(
                    "moja" to 1,
                    "Main-Class" to "IndexKt"
                )
                
                val debug by creating(
                    "objective" to "testing"
                ).join(common)
                
                val staging by creating().join(common)
                deploy(debug,staging)
            }
        """.trimIndent()
        )

        projectDir.root.resolveFile("settings.gradle.kts").makeParentsAndWriteText(
            """
            rootProject.name="test-project"
        """.trimIndent()
        )

        runner = GradleRunner.create()
            .withProjectDir(projectDir.root)
            .withPluginClasspath()
            .withGradleVersion(gradleVersion)
    }

    @Test
    fun `has environmentJsonDebug task`() {
        runner.withArguments(":environmentJsonDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":environmentJsonDebug")?.outcome)
        }
    }

    @Test
    fun `has fatJarDebug task`() {
        runner.withArguments(":fatJarDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":fatJarDebug")?.outcome)
        }
    }

    @Test
    fun `has fatJarStaging task`() {
        runner.withArguments(":fatJarStaging").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":fatJarStaging")?.outcome)
        }
    }

    @Test
    fun `runDebug task passess`() {
        runner.withArguments(":runDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":runDebug")?.outcome)
        }
    }

    @Test
    fun `runStaging task passess`() {
        runner.withArguments(":runStaging").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":runStaging")?.outcome)
        }
    }

    @AfterTest
    fun finalize() {
        val dst = File("build").resolveDir("tests/deployment")
        projectDir.root.copyRecursively(dst, true) { file, copyError ->
            System.err.println("Failed to copy $file due to ${copyError.message}")
            OnErrorAction.SKIP
        }
        println("Copied project to ${dst.absolutePath}")
    }
}