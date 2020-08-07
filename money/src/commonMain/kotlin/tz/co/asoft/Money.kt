package tz.co.asoft

import kotlinx.serialization.*
import kotlin.math.floor

/**
 * @param value should be in cents
 */
@Serializable(with = Money.Companion::class)
data class Money internal constructor(val value: Long, val cur: Currency) {
    constructor(value: Number, cur: Currency) : this((value.toDouble() * cur.smallestUnitMultiplier).toLong(), cur)

    init {
        require(cur.smallestUnitMultiplier > 0) {
            "small unit multiplier for ${cur.name} must be a non zero value"
        }
    }

    companion object : KSerializer<Money> {
        override val descriptor = SerialDescriptor("money")

        @OptIn(ExperimentalStdlibApi::class)
        override fun deserialize(decoder: Decoder): Money {
            val splits = buildList { repeat(7) { add(decoder.decodeString()) } }
            return Money(
                value = splits[0].toLong(),
                cur = Currency(
                    name = splits[6].toUpperCase(),
                    smallestUnitMultiplier = splits[3].toInt(),
                    smallestUnit = splits[1]
                )
            )
        }

        override fun serialize(encoder: Encoder, value: Money) {
            val cur = value.cur
            val unit = cur.smallestUnit
            val curName = cur.name
            encoder.encodeString("${value.value} $unit @ ${cur.smallestUnitMultiplier} $unit per $curName")
        }
    }

    operator fun times(n: Number) = Money((n.toDouble() * value).toLong(), cur)

    operator fun div(n: Number) = this * (1 / n.toDouble())

    operator fun plus(m: Money): Money {
        if (m.cur != cur) throw Exception("Can't add between two currencies ${m.cur.name}/${cur.name}")
        return Money(m.value + value, cur)
    }

    operator fun minus(m: Money): Money {
        if (m.cur != cur) throw Exception("Can't add between two currencies ${m.cur.name}/${cur.name}")
        return Money(value - m.value, cur)
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
        val amount = floor(value.toDouble() * 100) / (100 * cur.smallestUnitMultiplier)
        val splits = amount.toString().split(".")
        val characteristic = splits[0]
        val mantisa = splits.elementAtOrElse(1) { "0" }

        var counts = 0
        for (i in characteristic.length - 1 downTo 0) {
            counts++
            out = characteristic[i] + out
            if (counts == 3 && i != 0) {
                out = ",$out"
                counts = 0
            }
        }

        val mantisaValue = if (mantisa.length > 2) mantisa.take(2) else mantisa
        if (mantisaValue != "00" && mantisaValue != "0") {
            out += ".$mantisaValue"
        }
        return out
    }
}