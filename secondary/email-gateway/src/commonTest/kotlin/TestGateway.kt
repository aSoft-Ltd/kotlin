import tz.co.asoft.email.Email
import tz.co.asoft.email.entities.EmailMessage
import tz.co.asoft.email.gateway.IEmailGateway
import tz.co.asoft.io.File
import tz.co.asoft.persist.repo.IRepo

class TestGateway(override val repo: IRepo<EmailMessage>) : IEmailGateway {
    override suspend fun send(sender: String, receivers: List<Email>, subject: String?, body: String, attachments: List<File>): EmailMessage {
        return EmailMessage(sender, receivers.map { it.value }, subject, body).apply {
            println("New Email\nFrom: $sender\nTo: ${receivers.joinToString(",") { it.value }}")
            println("Subject: ${subject ?: "No Subject"}\n$body")
            println("Attachments: ${attachments.joinToString(",") { it.name }}")
            uid = repo.all().size.toString()
            repo.create(this)
        }
    }
}