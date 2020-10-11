import tz.co.asoft.*
import tz.co.asoft.test.asyncTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class RS512AlgorithmTest {

    val privateKey =
        "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJKkgd2Oxcgn8Br7kxlt0fLpCcBaM7l2NGrEyDXZOfX/pbtMBZSL9J4NhuVv2p3K1oVMgUDk3Ifcr1mXA/pcRIVueLxYh5r27S5f9uQeeuZh41v4ZnxB+r0H7PyZtatW/lq2guSZptPjDo2FkJOW7vOm6iED3wK8a08arvSYbDM7AgMBAAECgYBUZNLMhL902u9HSCO3Tcm3Z8CR8Be2k73E+K1UuEiwN8nUygKoJAfK8nQBFWeWyka9fnMgutXkZvCPueazLmjMECxh25Z5DKaMoZaDLya4ib3kW8YurIPZUrOH6QVi1pGiBuQWcZ2j+lZtdQC0wcCymns9EW3yhhF3ixrlSHd5YQJBAPenLNV3JtBYA28tC2PNMRA4H7WwICyG3cnZP/oUWxvb8TeVnbeI+iOjO4AGzyo0xJYGs3LNGg88hfQNzTh8FjECQQCXlcipeMx5ZD4IVvi4cHl8KynXha8tVXqtQWsoVTwIMtqY6j2bXv3uc/1B/glzCVYCw9C9T2A1iOJa+kh+qMkrAkB+q4p6EPzQWgQ/rEmEHaL3yc0mUFTUkZMFuFJJ5nmHcWMa7DMEelJ/QfGgFVuHGgBnQDwu/wtSOcHGR/zs5EMxAkB17deg0RPy1OPVxSPXCdnZmhMBU0XHtRqts5lUVYtQrY+XK5UmTS6s1pwPPT3Iu1io6gJ5ZUlqHMZNFS5VK6blAkAsbYWO9G144/YFGntOfoq2eL53BddS21DPamW7xpW7k1mCNfeHsbpd5sGJwC1asJKVU2sa89XyDvsxLm6CB5G9"

    val publicKey =
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSpIHdjsXIJ/Aa+5MZbdHy6QnAWjO5djRqxMg12Tn1/6W7TAWUi/SeDYblb9qdytaFTIFA5NyH3K9ZlwP6XESFbni8WIea9u0uX/bkHnrmYeNb+GZ8Qfq9B+z8mbWrVv5atoLkmabT4w6NhZCTlu7zpuohA98CvGtPGq70mGwzOwIDAQAB"

    @Test
    fun `should generate new pairs`() {
        assertNotNull(generateRSASecurityKeyPair())
    }

    @Test
    fun `should create a jwt with RS512 algorithm`() = asyncTest {
        val alg1 = RS512Algorithm(privateKey, publicKey)

        val jwt = alg1.createJWT {
            uid = "55"
            aid = "55"
            accountName = "anderson"
            userName = "anderson"
        }

        val token = jwt.token()
        assertEquals(3, token.split(".").size)
    }

    @Test
    fun `should verify without having algorithm instance`() {
        val token1 =
            "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6MjJ9.eyJ1aWQiOjU1LCJhaWQiOjU1LCJhY2NvdW50TmFtZSI6ImFuZGVyc29uIiwidXNlck5hbWUiOiJhbmRlcnNvbiJ9.f7WJCmqa/jjaxItBlbtwnVMuG1GYyArG7M36/e1nA/m3nfRYn+Wr5m5Gvquk0S5VfVM38gU0jXFoOCMyJkC24rEbnUhsieRv0gvEstLXPZT9APfFrIVIC3ZmJCAHnJpeNb1VFaVth9oxhjpj0UKqDPyy3E+RxofHY+EJBBYUMpI="
        val jwt1 = JWT.from(token1)
        val key1 = SecurityKey(uid = "0", value = publicKey)
        assertEquals(JWTVerification.Valid, verifyRS512(jwt1, key1))

        val token2 =
            "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6MjJ9.eyJ1aWQiOjU1LCJhaWQiOjU1LCJhY3NvdW50TmFtZSI6ImFuZGVyc29uIiwidXNlck5hbWUiOiJhbmRlcnNvbiJ9.f7WJCmqa/jjaxItBlbtwnVMuG1GYyArG7M36/e1nA/m3nfRYn+Wr5m5Gvquk0S5VfVM38gU0jXFoOCMyJkC24rEbnUhsieRv0gvEstLXPZT9APfFrIVIC3ZmJCAHnJpeNb1VFaVth9oxhjpj0UKqDPyy3E+RxofHY+EJBBYUMpI="
        val jwt2 = JWT.from(token2)
        assertNotEquals(token1, token2)
        assertEquals(JWTVerification.Invalid, verifyRS512(jwt2, key1))
    }
}