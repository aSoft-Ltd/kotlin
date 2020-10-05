import kotlinx.serialization.json.Json
import tz.co.asoft.*
import kotlin.test.Test
import kotlin.test.assertEquals

class JWTTest {
    @Test
    fun `header should look pretty`() {
        val header = JWTHeader()
        println(Json.stringify(JWTHeader.serializer(), header))
        assertEquals(header.alg, JWTHeader.Algorithm.RS512)
    }

    @Test
    fun `jwt can embed objects`() {
        val payload = JWTPayload(
            aid = "administrator",
            uid = "administrator",
            user = "System Administrator",
            account = "System Administrator",
            claimId = "administrator",
            claims = listOf()
        )

        val singedJwt = JWT.create(
            payload = payload,
            secret = "wajuba"
        )

        println("Created signed jwt:")
        println(singedJwt)
        println(singedJwt.token())

        val jwt = JWT.create(
            payload = payload
        )
        println("Created unsigned jwt:")
        println(jwt)
        println(jwt.token())
    }

    @Test
    fun `create a valid and secure RSASHA512 JWT token`() {
        val payload = JWTPayload(
            aid = "administrator",
            uid = "administrator",
            user = "System Administrator",
            account = "System Administrator",
            claimId = "administrator",
            claims = listOf()
        )

        val key = Key.generate()

        val signedJwt = JWT.create(
            payload = payload,
            secret = key.private
        )

        println(signedJwt.token())

        TODO("Complete this method")
//        assertEquals(JWTVerification.Valid, signedJwt.verify(key.private))
    }
}