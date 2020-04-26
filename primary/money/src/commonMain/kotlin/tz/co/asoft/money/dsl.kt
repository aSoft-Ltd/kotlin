package tz.co.asoft.money

operator fun Number.times(m: Money) = m * this
fun Number.toMoney(currency: Currency = Currency.TZS) = Money(this.toDouble(), currency)