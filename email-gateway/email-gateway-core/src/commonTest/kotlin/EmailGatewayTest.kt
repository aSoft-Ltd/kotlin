import tz.co.asoft.email.Email
import tz.co.asoft.io.File
import tz.co.asoft.persist.dao.cache
import tz.co.asoft.persist.repo.CachedRepo
import tz.co.asoft.test.AsyncTest
import kotlin.test.Test

@ExperimentalStdlibApi
class EmailGatewayTest : AsyncTest() {

    private val gateway = TestGateway(CachedRepo(cache()))

    @Test
    fun should_send_email() = asyncTest {
        val attachments = listOf(File("test file".encodeToByteArray(), "tmp.txt"))
        val msg = gateway.send(
                sender = "andy@lamax.com",
                receivers = listOf(Email("andy@lamax.com")),
                body = "Test Email",
                attachments = attachments
        )
        println(msg.uid)
        println()
        attachments.forEach {
            println(it.name + ":")
            println(it.readBytes().decodeToString())
        }
    }
}