package tz.co.asoft.money

data class Currency(val name: String = "") {
    companion object {
        val TZS = Currency("TZS")
    }
}