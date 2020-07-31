package tz.co.asoft

operator fun Number.times(m: Money) = m * this
fun Number.toMoney(currency: Currency = Currency.TZS) =
    Money(this.toDouble(), currency)
val Number.TZS get() = Money(
    this.toDouble(),
    Currency.TZS
)