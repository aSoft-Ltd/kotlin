package tz.co.asoft.sms.entities

data class DeliveryReport(val phone: String, val status: Status) {
    enum class Status {
        Pending,
        Submitted,
        Delivered,
        Failed
    }
}