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

class DeploymentExtensionJsTest {
    val projectDir = TemporaryFolder()
    val kotlinVersion = "1.3.72"
    val gradleVersion = "6.1.1"
    lateinit var runner: GradleRunner

    @BeforeTest
    fun setup() {
        projectDir.create()
        projectDir.root.resolveDir("build/kotlin-build/caches")

        projectDir.root.resolveFile("src/jsMain/kotlin/index.kt").makeParentsAndWriteText(
            """
            fun main() {
                println("This works")
            }
        """.trimIndent()
        )

        projectDir.root.resolveFile("src/jsMain/kotlin/index.kt").makeParentsAndWriteText(
            """
            fun main() {
                println("This works")
            }
        """.trimIndent()
        )

        projectDir.root.resolveFile("src/web/index.html").makeParentsAndWriteText(
            """
            <head><title>This Works</title></head>
            <body>
                This Works
            </body>
        """.trimIndent()
        )

        projectDir.root.resolveFile("build.gradle.kts").makeParentsAndWriteText(
            """
            plugins{
                kotlin("multiplatform")
                id("tz.co.asoft.deployment")
                id("tz.co.asoft.frontend")
            }
            
            repositories {
                jcenter()
                google()
                mavenCentral()
            }
            
            kotlin {
                js()
                sourceSets {
                    val jsMain by getting {
                        dependencies {
                            implementation(kotlin("stdlib-js"))
                        }
                    }
                }
            }
            
            kotlinFrontend {
                webpack{
                    contentPath = project.file("src/web")
                }
            }
            
            deployment {
                val common by creating("moja" to 1)
                
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
    fun `has good api`() {
        val env: DeploymentExtension? = null
        env?.apply {

            val common by creating(
                "objective" to "test",
                "subject" to mapOf(
                    "shared" to true,
                    "not-shared" to false
                )
            )

            val debug by creating(
                "juma" to 4
            ).join(common)

            val staging by creating().join(common)
            deploy(staging, debug)
        }
    }

    @Test
    fun `task targets runs successfully`() {
        runner.withArguments(":targets").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":targets")?.outcome)
        }
    }

    @Test
    fun `has environmentJsonDebug task`() {
        runner.withArguments(":environmentJsonDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":environmentJsonDebug")?.outcome)
        }
    }

    @Test
    fun `has bundleDebug task`() {
        runner.withArguments(":bundleDebug", "--stacktrace").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":bundleDebug")?.outcome)
        }
    }

    @Test
    fun `has bundleStaging task`() {
        runner.withArguments(":bundleStaging", "--stacktrace").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":bundleStaging")?.outcome)
        }
    }

    @Test
    fun `has run task`() {
        runner.withArguments(":runDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":runDebug")?.outcome)
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