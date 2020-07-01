package tz.co.asoft.gateway.sms.siltech

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.get
import io.ktor.http.encodeURLParameter
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import tz.co.asoft.persist.repo.IRepo
import tz.co.asoft.phone.Phone
import tz.co.asoft.gateway.sms.*
import tz.co.asoft.gateway.sms.DeliveryReport.Status

class SiltechSmsGateway(
    override val repo: IRepo<TextMessage>,
    private val apiKey: String
) : ISmsGateway {
    private val client = HttpClient { }

    private fun getData(
        sender: String,
        receivers: List<Phone>,
        body: String,
        type: TextMessage.Type
    ) = mutableListOf<FormPart<Any>>().apply {
        add(FormPart("key", apiKey))
        add(FormPart("campaign", 1))
        add(FormPart("routeid", 10))
        add(FormPart("type", type.name.toLowerCase()))
        add(FormPart("contacts", receivers.joinToString(",") { it.value }))
        add(FormPart("senderid", sender))
        add(FormPart("msg", body))
    }

    private suspend fun getRequest(
        sender: String,
        receivers: List<Phone>,
        body: String,
        type: TextMessage.Type
    ): TextMessage {
        val data = getData(
            sender,
            receivers,
            body,
            type
        ).joinToString("&") { "${it.key}=${it.value.toString().encodeURLParameter(true)}" }
        val response = client.get<String>("https://siltechtz.com/app/smsapi/index.php?$data")
        val txtMsg = TextMessage(
            sender = sender,
            recipients = receivers.map { it.value },
            type = type.name,
            body = body
        )
        if (!response.contains("SMS-SHOOT-ID")) {
            throw Throwable(
                "Failed to send sms to ${receivers.joinToString(
                    ",",
                    limit = 2
                )}: $response"
            )
        } else {
            txtMsg.uid = response.split("/")[1]
        }
        return repo.create(txtMsg)
    }

    override suspend fun send(
        sender: String,
        receivers: List<Phone>,
        body: String,
        type: TextMessage.Type
    ): TextMessage {
        return getRequest(sender, receivers, body, type)
    }

    private fun DeliveryResponse.toReport() = DeliveryReport(
        MSISDN, when (DLR) {
            "Operator Submitted" -> Status.Submitted
            "Delivered" -> Status.Delivered
            "Failed" -> Status.Failed
            else -> Status.Pending
        }
    )

    override suspend fun deliverStatus(uid: String): List<DeliveryReport> {
        val json = client.get<String>("https://siltechtz.com/app/miscapi/$apiKey/getDLR/$uid")
        return Json.parse(DeliveryResponse.serializer().list, json).map { it.toReport() }
    }
}