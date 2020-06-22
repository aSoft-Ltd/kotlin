package tz.co.asoft.siltech

import kotlinx.serialization.Serializable

@Serializable
class DeliveryResponse(
        val DESC: String = "",
        val DLR: String = "",
        val MSISDN: String = ""
)