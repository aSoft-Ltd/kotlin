import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

val RJson by lazy { Json(JsonConfiguration(ignoreUnknownKeys = true, prettyPrint = true)) }