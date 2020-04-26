import tz.co.asoft.platform.env.toJsonObject
import tz.co.asoft.platform.env.toMap
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonTest {
    @Test
    fun should_read_json() {
        val env = """{"name":"test"}""".toJsonObject().toMap()
        assertEquals("test", env["name"])
    }
}