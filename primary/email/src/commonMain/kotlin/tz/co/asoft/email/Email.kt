package tz.co.asoft.email

class Email(value: String) {
    var value: String = ""
        private set

    init {
        val parts = value.split("@")
        if (parts.size == 2 && parts.getOrNull(1)?.contains(".") == true) {
            this.value = value
        } else {
            throw Throwable("Invalid email: $value")
        }
    }
}