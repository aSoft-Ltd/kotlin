package tz.co.asoft

class RS512Algorithm(source: KeyRotator) : JWTAlgorithm("RS512", source, RS512Signer) {
    constructor(privateKey: String, publicKey: String) : this(LinearKeyRotator<SecurityKeyPair>(
        maxKeys = 1,
        source = InMemoryKeySource<SecurityKeyPair>(),
        generator = { SecurityKeyPair(privateKey = privateKey, publicKey = publicKey) }
    ))

    companion object {
        const val ALGO_NAME = "SHA512withRSA"
    }
}