import kotlinx.coroutines.coroutineScope
import tz.co.asoft.*
import tz.co.asoft.test.asyncTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RS512KeyRotationTest {

    @Test
    fun `should create a jwt with HS256 algorithm`() = asyncTest {
        val rotator = LinearKeyRotator(
            maxKeys = 4,
            source = InMemoryKeySource(),
            generator = keyPairGenerator
        )
        val alg1 = RS512Algorithm(rotator)
        val jwts = coroutineScope {
            List(4) {
                alg1.createJWT {
                    uid = "55"
                    aid = "55"
                    accountName = "anderson"
                    userName = "anderson"
                }
            }
        }
        val ids = jwts.map { it.header.kid }.toString()
        assertEquals(listOf(0, 1, 2, 3).toString(), ids)
    }
}