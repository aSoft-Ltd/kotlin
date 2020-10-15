import tz.co.asoft.JWT
import tz.co.asoft.JWTVerification
import tz.co.asoft.SecurityKey

fun JWT.verifyTSTALG(key: SecurityKey): JWTVerification {
    return if (TestSigner.sign(this, key).signature == signature) {
        JWTVerification.Valid
    } else {
        JWTVerification.Invalid
    }
}