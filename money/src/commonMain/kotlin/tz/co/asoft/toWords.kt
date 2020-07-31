package tz.co.asoft

private fun Int.convertLessThanOneThousand(): String {
    var number = this
    var current: String

    if (number % 100 < 20) {
        current = numNames[number % 100]
        number /= 100
    } else {
        current = numNames[number % 10]
        number /= 10

        current = tensNames[number % 10] + current
        number /= 10
    }
    return if (number == 0) current else numNames[number] + " hundred" + current
}

fun Int.convert(): String {
    var number = this

    if (number == 0) {
        return "zero"
    }

    var prefix = ""

    if (number < 0) {
        number = -number
        prefix = "negative"
    }

    var current = ""
    var place = 0

    do {
        val n = number % 1000
        if (n != 0) {
            val s = n.convertLessThanOneThousand()
            current = s + specialNames[place] + current
        }
        place++
        number /= 1000
    } while (number > 0)

    return (prefix + current).trim { it <= ' ' }
}


private val specialNames = arrayOf("", " thousand", " million", " billion", " trillion", " quadrillion", " quintillion")

private val tensNames = arrayOf("", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy", " eighty", " ninety")

private val numNames = arrayOf("", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen", " nineteen")