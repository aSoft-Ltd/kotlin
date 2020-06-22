import tz.co.asoft.persist.dao.cache
import tz.co.asoft.persist.repo.CachedRepo
import tz.co.asoft.phone.Phone
import tz.co.asoft.gateway.sms.siltech.SiltechSmsGateway
import tz.co.asoft.test.AsyncTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
class SiltechSmsGatewayTest : AsyncTest() {
    private val gateway = SiltechSmsGateway(
        CachedRepo(cache()),
        "35E4C86A0C1A31"
    )

    @Test
    fun should_send_a_text_msg() = asyncTest {
        val msg = gateway.sendTextMsg("15076", listOf(Phone(620296750)), "Testing for a terminal")
        println("Delivery: ${gateway.deliverStatus(msg.uid)}")
    }
}