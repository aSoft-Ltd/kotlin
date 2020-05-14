package extensions

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import tools.makeParentsAndWriteText
import tz.co.asoft.deployment.extensions.DeploymentExtension
import tz.co.asoft.deployment.tools.resolveDir
import tz.co.asoft.deployment.tools.resolveFile
import java.io.File
import kotlin.test.*

class DeploymentExtensionJvmTest {
    val projectDir = TemporaryFolder()
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

        projectDir.root.resolveFile("src/paidMain/resources/index.txt").makeParentsAndWriteText(
            """
                resources test
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
                jvm("paid")
                jvm("free")
                sourceSets {
                    val allJvm by creating {
                        dependencies {
                            implementation(kotlin("stdlib"))
                        }
                    }
                    val jvmMain by getting {
                        dependsOn(allJvm)
                    }
                    
                    val paidMain by getting {
                        dependsOn(jvmMain)
                    }
                    
                    val freeMain by getting {
                        dependsOn(jvmMain)
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
    fun `has environmentJsonJvmDebug task`() {
        runner.withArguments(":environmentJsonJvmDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":environmentJsonJvmDebug")?.outcome)
        }
    }

    @Test
    fun `has environmentJsonPaidDebug task`() {
        runner.withArguments(":environmentJsonPaidDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":environmentJsonPaidDebug")?.outcome)
        }
    }

    @Test
    fun `has fatJarJvmDebug task`() {
        runner.withArguments(":fatJarJvmDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":fatJarJvmDebug")?.outcome)
        }
    }

    @Test
    fun `has fatJarJvmStaging task`() {
        runner.withArguments(":fatJarJvmStaging").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":fatJarJvmStaging")?.outcome)
        }
    }

    @Test
    fun `runJvmDebug task passess`() {
        runner.withArguments(":runJvmDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":runJvmDebug")?.outcome)
        }
    }

    @Test
    fun `runJvmStaging task passess`() {
        runner.withArguments(":runJvmStaging").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":runJvmStaging")?.outcome)
        }
    }

    @Test
    fun `has fatJarPaidStaging task`() {
        runner.withArguments(":fatJarPaidStaging").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":fatJarPaidStaging")?.outcome)
        }
    }

    @Test
    fun `has paidJar task`() {
        runner.withArguments(":paidJar").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":paidJar")?.outcome)
        }
    }

    @Test
    fun `runPaidStaging task passess`() {
        runner.withArguments(":runPaidStaging").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":runPaidStaging")?.outcome)
        }
    }

    @Test
    fun `runFreeStaging task passess`() {
        runner.withArguments(":runFreeStaging").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":runFreeStaging")?.outcome)
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