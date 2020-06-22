package tz.co.asoft.email.gateway

import tz.co.asoft.email.Email
import tz.co.asoft.email.entities.EmailMessage
import tz.co.asoft.io.File
import tz.co.asoft.persist.repo.IRepo

interface IEmailGateway {
    val repo: IRepo<EmailMessage>
    suspend fun send(sender: String, receivers: List<Email>, subject: String? = null, body: String, attachments: List<File> = listOf()): EmailMessage
}