package tz.co.asoft.klock

fun Number.asDateTime() = DateTime.fromUnix(this.toLong())

fun DateTimeTz.formated() = format("yyyy-MM-dd HH:mm:ss")

fun Number.asFormatedDate() = asDateTime().local.formated()

fun Number.asFormatedDateOnly() = asDateTime().local.format("yyyy-MM-dd")

fun Number.toDateMonthYear() = asDateTime().local.format("dd-MM-yyyy")

fun Number.asFormatedTimeOnly() = asDateTime().local.format("HH:mm:ss")

fun Number.asFormated(dateFormat: String) = asDateTime().local.format(dateFormat)
