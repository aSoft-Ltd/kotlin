import tz.co.asoft.email.Email
import kotlin.test.*

class EmailTest {

    @Test
    fun should_pass() {
        assertEquals("andy@lamax.com", Email("andy@lamax.com").value)
    }

    @Test
    fun should_fail() {
        assertFails { Email("kitimtim") }
        assertFails { Email("test.com") }
    }
}