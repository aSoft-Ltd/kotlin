package tz.co.asoft

import kotlin.math.floor

data class Money(var value: Double = 0.0, var cur: Currency = Currency.TZS) {

    operator fun times(n: Number) = Money(n.toDouble() * value, cur)

    operator fun plus(m: Money): Money {
        if (m.cur != cur) throw Exception("Can't add between two currencies ${m.cur.name}/${cur.name}")
        return Money(m.value + value, cur)
    }

    operator fun minus(m: Money): Money {
        if (m.cur != cur) throw Exception("Can't add between two currencies ${m.cur.name}/${cur.name}")
        return Money(value - m.value, cur)
    }

    operator fun plusAssign(m: Money) {
        if (m.cur != cur) throw Exception("Can't add between two currencies ${m.cur.name}/${cur.name}")
        value += m.value
    }

    operator fun compareTo(m: Money): Int {
        if (m.cur != cur) {
            throw Exception("Can't compare different currencies")
        }
        return value.compareTo(m.value)
    }

    fun toFullString(): String = "${cur.name} $this"

    override fun toString(): String {
        var out = ""
        val amount = floor(value * 100) / 100
        val splits = amount.toString().split(".")
        val characteristic = splits[0]
        val mantisa = splits.elementAtOrElse(1) { "0" }
        var counts = 0
        for (i in characteristic.length downTo 0) {
            out = characteristic[i] + out
            if (counts == 3 && i != 0) {
                out = ",$out"
                counts = 0
            }
            counts++
        }
        val mantisaValue = (if (mantisa.toInt() < 10) "0" else "") + mantisa.toInt()
        if (mantisaValue != "00") {
            out += ".$mantisaValue"
        }
        return out
    }
}