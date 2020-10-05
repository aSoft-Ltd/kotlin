package tz.co.asoft

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey

actual class Key(
    val privateKey: PrivateKey,
    val publicKey: PublicKey
) {
    constructor(kp: KeyPair) : this(kp.private, kp.public)

    actual val private get() = privateKey.toBase64()
    actual val public get() = publicKey.toBase64()

    actual companion object
}