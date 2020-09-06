import tz.co.asoft.*
import kotlin.test.Test

class ConsoleAppenderTest {
    @Test
    fun `api should look good`() {
        console.i("Testing again", "first" to 1, "second" to 2)
        console.e("This is a test error", "correct" to false)
    }

    @Test
    fun `should be able to take the logger of test class`() {
        val logger = logger()
        logger.d("Debug test")
        logger.i("Info test")
        logger.w("Warning test")
        logger.e("Error test")
        logger.f("Failure test")
    }

    @Test
    fun `should print less verbose errors`() {
        val logger = logger(verbose = false)
        logger.d("Debug test")
        logger.i("Info test")
        logger.w("Warning test")
        logger.e("Error test")
        logger.f("Failure test")
    }

    @Test
    fun `should print by levels`() {
        val logger = logger(source = "Level Test", level = LogLevel.ERROR)
        logger.d("Debug test")
        logger.i("Info test")
        logger.w("Warning test")
        logger.e("Error test")
        logger.f("Failure test")
    }
}