package tasks

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import tools.makeParentsAndWriteText
import tz.co.asoft.deployment.tools.resolveDir
import tz.co.asoft.deployment.tools.resolveFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EnvironmentJsonTaskTest {
    val projectDir = TemporaryFolder()
    val kotlinVersion = "1.3.72"
    val gradleVersion = "6.1.1"
    lateinit var runner: GradleRunner

    @BeforeTest
    fun setup() {
        projectDir.create()
        projectDir.root.resolveDir("build/kotlin-build/caches")

        projectDir.root.resolveFile("build.gradle.kts").makeParentsAndWriteText(
            """
            plugins{
                kotlin("multiplatform")
                id("tz.co.asoft.deployment")
            }
            
            kotlin {
                js()
            }
        """.trimIndent()
        )

        runner = GradleRunner.create()
            .withProjectDir(projectDir.root)
            .withPluginClasspath()
            .withGradleVersion(gradleVersion)
    }

    @Test
    fun `task runs successfully`() {
        runner.withArguments(":targets").build().apply {
            val outcome = task(":targets")?.outcome
            println("Task outcome: $outcome")
            assertEquals(TaskOutcome.SUCCESS, outcome)
        }
    }
}