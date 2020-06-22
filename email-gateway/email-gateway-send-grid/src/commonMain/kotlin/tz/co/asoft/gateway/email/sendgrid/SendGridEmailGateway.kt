package tz.co.asoft.gateway.email.sendgrid

import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import tz.co.asoft.email.Email
import tz.co.asoft.gateway.email.core.*
import tz.co.asoft.io.File
import tz.co.asoft.persist.repo.IRepo

class SendGridEmailGateway(
    override val repo: IRepo<EmailMessage>,
    private val apiKey: String
) : IEmailGateway {
    private val client = HttpClient { }
    private val url = "https://api.sendgrid.com/v3/mail/send"
    private fun content(
        sender: String,
        receivers: List<Email>,
        subject: String?,
        body: String,
        type: String
    ): String {
        val cntnt = """
        {
          "personalizations": [
            {
              "to": [${receivers.joinToString(",") { """{ "email":"${it.value}"}""" }}]
            }
          ],
          "from": {
            "email": "$sender"
          },
          "subject": "${subject ?: "No Subject"}",
          "content": [
            {
              "type": "$type",
              "value": "$body"
            }
          ]
        }
    """.trimIndent()
        return cntnt
    }

    override suspend fun send(
        sender: String,
        receivers: List<Email>,
        subject: String?,
        body: String,
        attachments: List<File>
    ): EmailMessage {
        client.post<String>(url) {
            header("Authorization", "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            this.body = content(sender, receivers, subject, body, "text/html")
        }
        val email = EmailMessage(sender, receivers.map { it.value }, subject, body)
        return repo.create(email)
    }
}