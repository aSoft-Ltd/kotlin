package tz.co.asoft.gateway.sms

data class DeliveryReport(val phone: String, val status: Status) {
    enum class Status {
        Pending,
        Submitted,
        Delivered,
        Failed
    }
}