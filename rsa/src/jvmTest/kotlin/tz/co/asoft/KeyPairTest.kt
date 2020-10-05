import org.junit.Test
import tz.co.asoft.*
import tz.co.asoft.Key
import java.security.*
import javax.xml.bind.DatatypeConverter

class KeyPairTest {

    val keyLength = 1024 * 3

    private fun showKeys(pair: KeyPair) {
        val (priv, pub) = pair

        println(DatatypeConverter.printHexBinary(priv.encoded))
        println(DatatypeConverter.printHexBinary(pub.encoded))

        println(priv.toBase64())
        println(pub.toBase64())
    }

    @Test
    fun `api should look good`() {
        val kpg = KeyPairGenerator.getInstance("DSA", "SUN")
        val random = SecureRandom.getInstance("SHA1PRNG", "SUN")
        kpg.initialize(keyLength, random)
        println("DSA,SUN")
        showKeys(kpg.genKeyPair())
        println("\n\n")
    }

    @Test
    fun `api2 should look good`() {
        val kpg = KeyPairGenerator.getInstance("RSA").apply {
            initialize(keyLength, SecureRandom())
        }
        println("RSA")
        showKeys(kpg.genKeyPair())
        println("\n\n")
    }

    @Test
    fun `should easily generate public private keys`() {
        val key = Key.generate()
        println(key)
    }
}