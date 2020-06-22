import tz.co.asoft.persist.dao.Cache
import tz.co.asoft.persist.repo.Repo
import tz.co.asoft.phone.Phone
import tz.co.asoft.sms.entities.TextMessage
import tz.co.asoft.test.AsyncTest
import kotlin.test.Test

class GatewayTest : AsyncTest() {
    val cache = Cache<TextMessage>()
    val repo = Repo(cache)
    val gateWay = TestGateway(repo)

    @Test
    fun instantiate_gateway() = asyncTest {
        val msg1 = gateWay.sendTextMsg("ANDY", listOf(Phone(Phone.fake)), "Test Message")
        val msg2 = gateWay.sendFlashMsg("ANDY", listOf(Phone(752748674)), "Test flash message")
        val msg3 = gateWay.sendUnicode("ANDY", listOf(Phone(Phone.fake)), "Hi ${128514.toChar()}")

        println(gateWay.deliverStatus(msg1.uid))
        println(gateWay.deliverStatus(msg2.uid))
        println(gateWay.deliverStatus(msg3.uid))
    }
}