import tz.co.asoft.persist.repo.IRepo
import tz.co.asoft.phone.Phone
import tz.co.asoft.sms.entities.DeliveryReport
import tz.co.asoft.sms.entities.TextMessage
import tz.co.asoft.sms.gateway.ISmsGateway
import tz.co.asoft.sms.entities.TextMessage.Type

class TestGateway(override val repo: IRepo<TextMessage>) : ISmsGateway {
    override suspend fun send(sender: String, receivers: List<Phone>, body: String, type: Type): TextMessage {
        println("New ${type.name} Message:")
        println("From: $sender")
        println("To: ${receivers.joinToString(",") { it.value }}")
        println(body)
        val txtMsg = TextMessage(sender, receivers.map { it.value }, body, type.name).apply {
            uid = repo.all().size.toString()
        }
        return repo.create(txtMsg)
    }

    override suspend fun deliverStatus(uid: String): List<DeliveryReport> {
        return repo.load(uid)?.recipients?.map { DeliveryReport(it, DeliveryReport.Status.Delivered) }
                ?: listOf()
    }
}