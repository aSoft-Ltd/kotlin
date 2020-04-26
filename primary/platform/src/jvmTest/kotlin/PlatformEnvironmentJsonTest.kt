import tz.co.asoft.platform.env.getEnv
import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformEnvironmentJsonTest {
    @Test
    fun should_read_from_file() {
        val env = getEnv()
        assertEquals("test", env["name"])
    }
}