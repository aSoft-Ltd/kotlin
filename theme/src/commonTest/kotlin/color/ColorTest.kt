package color

import tz.co.asoft.color.Color
import kotlin.test.Test

class ColorTest {
    @Test
    fun `should instantiate properly`() {
        val c = Color(0xFF0000)
        println(c)
    }
}