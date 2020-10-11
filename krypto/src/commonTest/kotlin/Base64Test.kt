import tz.co.asoft.Base64
import tz.co.asoft.ascii
import tz.co.asoft.toByteArray
import kotlin.test.Test

class Base64Test {
    @Test
    fun `should convert json to base 64`() {
        val json = """{ "uid": 4 }"""
        val base64 = Base64.encode(json.toByteArray())
        println(base64)
        val js = Base64.decode(base64).ascii
        println(js)
    }
}