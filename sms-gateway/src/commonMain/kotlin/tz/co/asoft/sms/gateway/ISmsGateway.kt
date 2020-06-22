package tz.co.asoft.sms.gateway

import tz.co.asoft.persist.repo.IRepo
import tz.co.asoft.phone.Phone
import tz.co.asoft.sms.entities.DeliveryReport
import tz.co.asoft.sms.entities.TextMessage

interface ISmsGateway {
    val repo: IRepo<TextMessage>
    suspend fun send(sender: String, receivers: List<Phone>, body: String, type: TextMessage.Type): TextMessage
    suspend fun sendTextMsg(sender: String, receivers: List<Phone>, body: String) = send(sender, receivers, body, TextMessage.Type.Text)
    suspend fun sendUnicode(sender: String, receivers: List<Phone>, body: String) = send(sender, receivers, body, TextMessage.Type.Unicode)
    suspend fun sendFlashMsg(sender: String, receivers: List<Phone>, body: String) = send(sender, receivers, body, TextMessage.Type.Flash)
    suspend fun deliverStatus(uid: String): List<DeliveryReport>
}