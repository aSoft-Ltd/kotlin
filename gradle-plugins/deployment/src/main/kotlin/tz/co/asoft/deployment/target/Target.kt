package tz.co.asoft.deployment.target

import java.io.Serializable

data class Target(val name: String, var values: Map<String, Any>) : Serializable
