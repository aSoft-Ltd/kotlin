package tz.co.asoft

class HS256Algorithm(rotator: KeyRotator) : JWTAlgorithm("HS256", rotator, HS256Signer) {
    constructor(secret: String) : this(LinearKeyRotator(
        maxKeys = 1,
        source = InMemoryKeySource<SecurityKey>(),
        generator = { SecurityKey(uid = "0", value = secret) }
    ))
}