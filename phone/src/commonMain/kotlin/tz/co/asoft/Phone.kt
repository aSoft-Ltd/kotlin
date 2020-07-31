package tz.co.asoft

class Phone {
    constructor(value: Int) : this(value.toString())
    constructor(value: Long) : this(value.toString())
    constructor(value: String) {
        this.value = value.parse()
        if (!isValid) {
            throw Throwable("Invalid phone number $value")
        }
    }

    var value: String = ""
        private set

    private val isValid get() = value.length == 12

    private fun String.parse(): String {
        var parsedValue = this
        if (parsedValue.startsWith("0")) {
            parsedValue = parsedValue.substring(1)
            return parsedValue.parse()
        }

        if (parsedValue.startsWith("+")) {
            parsedValue = parsedValue.substring(1)
            return parsedValue.parse()
        }

        if (parsedValue.length == 9) {
            parsedValue = "255$parsedValue"
        }
        return parsedValue
    }

    companion object {
        val fakeProviderNumber = listOf(22, 61, 65, 67, 68, 71, 74, 75, 76, 78)
        val fake get() = "255" + fakeProviderNumber.random() + (1111111..9999999).random()
    }
}