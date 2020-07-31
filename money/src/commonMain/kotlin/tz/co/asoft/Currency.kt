package tz.co.asoft

data class Currency(val name: String = "") {
    companion object {
        val TZS = Currency("TZS")
    }
}