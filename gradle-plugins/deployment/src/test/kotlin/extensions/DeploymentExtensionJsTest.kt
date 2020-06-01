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

@Ignore
class DeploymentExtensionJsTest {
    val projectDir = TemporaryFolder()
    val gradleVersion = "6.1.1"
    lateinit var runner: GradleRunner

    @BeforeTest
    fun setup() {
        projectDir.create()
        projectDir.root.resolveDir("build/kotlin-build/caches")

        projectDir.root.resolveFile("src/jsMain/kotlin/index.kt").makeParentsAndWriteText(
            """
            @JsModule("platform.environment.json")
            @JsNonModule
            internal external val platformEnvironment: Any
            
            fun main() {
                println("This works")
                console.log(platformEnvironment)
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
                js() {
                    useCommonJs()
                }
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
//            .withGradleVersion(gradleVersion)
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
    fun `has environmentJsonJsDebug task`() {
        runner.withArguments(":environmentJsonJsDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":environmentJsonJsDebug")?.outcome)
        }
    }

    @Test
    fun `can deploy separately`() {
        arrayOf(":deployJsDebug", ":deployJsStaging").forEach { task ->
            runner.withArguments(task).build().apply {
                println(output)
                assertEquals(TaskOutcome.SUCCESS, task(task)?.outcome)
            }
        }
    }

    @Test
    fun `has bundleJsDebug task`() {
        runner.withArguments(":bundleJsDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":bundleJsDebug")?.outcome)
        }
    }

    @Test
    fun `has bundleJsStaging task`() {
        runner.withArguments(":bundleJsStaging", "--stacktrace").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":bundleJsStaging")?.outcome)
        }
    }

    @Test
    fun `has run task`() {
        runner.withArguments(":runJsDebug").build().apply {
            println(output)
            assertEquals(TaskOutcome.SUCCESS, task(":runJsDebug")?.outcome)
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