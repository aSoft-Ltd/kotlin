import tz.co.asoft.*
import kotlin.test.Test

class AppenderTest {
    @Test
    fun `api should look good`() {
        Console.i("Testing again", "first" to 1, "second" to 2)
        Console.e("This is a test error", "correct" to false)
    }

    @Test
    fun `should be able to take the logger of test class`() {
        val logger = logger()
        logger.i("Info test")
        logger.d("Debug test")
        logger.w("Warning test")
        logger.e("Error test")
        logger.f("Failure test")
    }

    @Test
    fun `should print less verbose errors`() {
        val logger = logger(verbose = false)
        logger.i("Info test")
        logger.d("Debug test")
        logger.w("Warning test")
        logger.e("Error test")
        logger.f("Failure test")
    }
}