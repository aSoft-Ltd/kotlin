import tz.co.asoft.*
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class HS256AlgorithmTest {
    @Test
    fun `should create a jwt with HS256 algorithm`() {
        val alg1 = HS256Algorithm("wakubwawenu")
        val alg2 = HS256Algorithm("anothersecret")
        val alg3 = HS256Algorithm("wakubwawenu")

        val jwt = alg1.createJWT {
            header = JWTHeader {
                kid = "22"
            }
            payload = JWTPayload {
                uid = "55"
                aid = "55"
                accountName = "anderson"
                userName = "anderson"
            }
        }
        val token = jwt.token()
        println(jwt)
        println(token)
        assertEquals(3, token.split(".").size)

        assertEquals(JWTVerification.Valid, alg1.verify(jwt))
        assertEquals(JWTVerification.Invalid, alg2.verify(jwt))
        assertEquals(JWTVerification.Valid, alg3.verify(jwt))
    }

    @Test
    fun `should verify without having algorithm instance`() {
        val token1 =
            """eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6MjJ9.eyJ1aWQiOjU1LCJhaWQiOjU1LCJhY2NvdW50TmFtZSI6ImFuZGVyc29uIiwidXNlck5hbWUiOiJhbmRlcnNvbiJ9.GSSXToX/ce6F9fMX68NrDJSpJeNqV66DwCTFYesvq8g"""
        val jwt1 = JWT.from(token1)
        assertEquals("HS256", jwt1.header.alg)
        val ver1 = HS256Verifier("secret")
        assertEquals(JWTVerification.Invalid, ver1.verify(jwt1))
        val ver2 = HS256Verifier("wakubwawenu")
        assertEquals(JWTVerification.Valid, ver2.verify(jwt1))

        val token2 =
            """eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6MjJ9.eyJ1aWQiOjU1LCJhaWQiOjU1LCJhY2NvdW50TmFtZSI6ImFuZGVyc29uIiwidXNlck5hbWUiOiJBbmRlcnNvbiJ9.GSSXToX/ce6F9fMX68NrDJSpJeNqV66DwCTFYesvq8g"""
        val jwt2 = JWT.from(token2)
        println(jwt2.payload)
        assertEquals(JWTVerification.Invalid, ver2.verify(jwt2))
    }
}