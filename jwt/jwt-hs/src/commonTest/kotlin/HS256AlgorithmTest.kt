import tz.co.asoft.*
import tz.co.asoft.test.asyncTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class HS256AlgorithmTest {

    @Test
    fun `should create a jwt with HS256 algorithm`() = asyncTest {
        val alg1 = HS256Algorithm("wakubwawenu")
        val jwt = alg1.createJWT {
            uid = "55"
            aid = "55"
            accountName = "anderson"
            userName = "anderson"
        }
        val token = jwt.token()
        println(jwt)
        println(token)
        assertEquals(3, token.split(".").size)
    }

    @Test
    fun `should verify without having algorithm instance`() {
        val token1 =
            """eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6MjJ9.eyJ1aWQiOjU1LCJhaWQiOjU1LCJhY2NvdW50TmFtZSI6ImFuZGVyc29uIiwidXNlck5hbWUiOiJhbmRlcnNvbiJ9.GSSXToX_ce6F9fMX68NrDJSpJeNqV66DwCTFYesvq8g"""
        val jwt1 = JWT.from(token1)
        assertEquals("HS256", jwt1.header.alg)
        val key1 = SecurityKey(uid = "1", value = "secret")
        assertEquals(JWTVerification.Invalid, verifyHS256(jwt1, key1))
        val key2 = SecurityKey(uid = "2", value = "wakubwawenu")
        assertEquals(JWTVerification.Valid, verifyHS256(jwt1, key2))

        val token2 =
            """eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6MjJ9.eyJ1aWQiOjU1LCJhaWQiOjU1LCJhY2NvdW50TmFtZSI6ImFuZGVyc29uIiwidXNlck5hbWUiOiJBbmRlcnNvbiJ9.GSSXToX_ce6F9fMX68NrDJSpJeNqV66DwCTFYesvq8g"""
        val jwt2 = JWT.from(token2)
        println(jwt2.payload)
        assertEquals(JWTVerification.Invalid, verifyHS256(jwt2, key2))
    }
}