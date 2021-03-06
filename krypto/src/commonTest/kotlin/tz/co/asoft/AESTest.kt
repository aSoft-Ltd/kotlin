package tz.co.asoft

import kotlin.test.*

class AESTest {
	@Test
	fun name() {
		val plainText = Hex.decode("00112233445566778899aabbccddeeff")
		val cipherKey = Hex.decode("000102030405060708090a0b0c0d0e0f")
		val cipherText = Hex.decode("69c4e0d86a7b0430d8cdb78070b4c55a")
		assertEquals(plainText.toHexStringLower(), AES.decryptAes128Cbc(cipherText, cipherKey).toHexStringLower())
		assertEquals(cipherText.toHexStringLower(), AES.encryptAes128Cbc(plainText, cipherKey).toHexStringLower())
	}

	@Test
	fun longName() {
		val plainText = Hex.decode("00112233445566778899aabbccddeeff00112233445566778899aabbccddeeff00112233445566778899aabbccddeeff")
		val cipherKey = Hex.decode("000102030405060708090a0b0c0d0e0f")
		val cipherText = AES.encryptAes128Cbc(plainText, cipherKey)
		assertEquals(plainText.toHexStringLower(), AES.decryptAes128Cbc(cipherText, cipherKey).toHexStringLower())
	}

    @Test
    fun pkcs7padding() {
        val data = ByteArray(128){it.toByte()}
        for (i in data.indices) {
            // plainText is [0 .. i]
            val plainText = ByteArray(i){it.toByte()}
            println(plainText.contentToString())
            val cipherKey = byteArrayOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4)
            val encryptedText = AES.encryptAes128Cbc(plainText, cipherKey, Padding.PKCS7Padding)
            val decryptedText = AES.decryptAes128Cbc(encryptedText, cipherKey, Padding.PKCS7Padding)
            assertEquals(plainText.toHexStringLower(), decryptedText.toHexStringLower())
        }
    }
}

/*
http://www.herongyang.com/Cryptography/AES-Example-Vector-of-AES-Encryption.html

Plaintext:  00112233445566778899aabbccddeeff
Cipher key: 000102030405060708090a0b0c0d0e0f
Ciphertext: 69c4e0d86a7b0430d8cdb78070b4c55a

Encryption rounds, round keys and state values:

Round 0:
   state:      00112233445566778899aabbccddeeff <-- Plaintext
   round key:  000102030405060708090a0b0c0d0e0f
   state:      00102030405060708090a0b0c0d0e0f0 <-- AddRoundKey()

Round 1:
   state:      63cab7040953d051cd60e0e7ba70e18c <-- SubBytes()
   state:      6353e08c0960e104cd70b751bacad0e7 <-- ShiftRows()
   state:      5f72641557f5bc92f7be3b291db9f91a <-- MixColumns()
   round key:  d6aa74fdd2af72fadaa678f1d6ab76fe
   state:      89d810e8855ace682d1843d8cb128fe4 <-- AddRoundKey()

Round 2:
   state:      a761ca9b97be8b45d8ad1a611fc97369 <-- SubBytes()
   state:      a7be1a6997ad739bd8c9ca451f618b61 <-- ShiftRows()
   state:      ff87968431d86a51645151fa773ad009 <-- MixColumns()
   round key:  b692cf0b643dbdf1be9bc5006830b3fe
   state:      4915598f55e5d7a0daca94fa1f0a63f7 <-- AddRoundKey()

Round 3:
   state:      3b59cb73fcd90ee05774222dc067fb68 <-- SubBytes()
   state:      3bd92268fc74fb735767cbe0c0590e2d <-- ShiftRows()
   state:      4c9c1e66f771f0762c3f868e534df256 <-- MixColumns()
   round key:  b6ff744ed2c2c9bf6c590cbf0469bf41
   state:      fa636a2825b339c940668a3157244d17 <-- AddRoundKey()

Round 4:
   state:      2dfb02343f6d12dd09337ec75b36e3f0 <-- SubBytes()
   state:      2d6d7ef03f33e334093602dd5bfb12c7 <-- ShiftRows()
   state:      6385b79ffc538df997be478e7547d691 <-- MixColumns()
   round key:  47f7f7bc95353e03f96c32bcfd058dfd
   state:      247240236966b3fa6ed2753288425b6c <-- AddRoundKey()

Round 5:
   state:      36400926f9336d2d9fb59d23c42c3950 <-- SubBytes()
   state:      36339d50f9b539269f2c092dc4406d23 <-- ShiftRows()
   state:      f4bcd45432e554d075f1d6c51dd03b3c <-- MixColumns()
   round key:  3caaa3e8a99f9deb50f3af57adf622aa
   state:      c81677bc9b7ac93b25027992b0261996 <-- AddRoundKey()

Round 6:
   state:      e847f56514dadde23f77b64fe7f7d490 <-- SubBytes()
   state:      e8dab6901477d4653ff7f5e2e747dd4f <-- ShiftRows()
   state:      9816ee7400f87f556b2c049c8e5ad036 <-- MixColumns()
   round key:  5e390f7df7a69296a7553dc10aa31f6b
   state:      c62fe109f75eedc3cc79395d84f9cf5d <-- AddRoundKey()

Round 7:
   state:      b415f8016858552e4bb6124c5f998a4c <-- SubBytes()
   state:      b458124c68b68a014b99f82e5f15554c <-- ShiftRows()
   state:      c57e1c159a9bd286f05f4be098c63439 <-- MixColumns()
   round key:  14f9701ae35fe28c440adf4d4ea9c026
   state:      d1876c0f79c4300ab45594add66ff41f <-- AddRoundKey()

Round 8:
   state:      3e175076b61c04678dfc2295f6a8bfc0 <-- SubBytes()
   state:      3e1c22c0b6fcbf768da85067f6170495 <-- ShiftRows()
   state:      baa03de7a1f9b56ed5512cba5f414d23 <-- MixColumns()
   round key:  47438735a41c65b9e016baf4aebf7ad2
   state:      fde3bad205e5d0d73547964ef1fe37f1 <-- AddRoundKey()

Round 9:
   state:      5411f4b56bd9700e96a0902fa1bb9aa1 <-- SubBytes()
   state:      54d990a16ba09ab596bbf40ea111702f <-- ShiftRows()
   state:      e9f74eec023020f61bf2ccf2353c21c7 <-- MixColumns()
   round key:  549932d1f08557681093ed9cbe2c974e
   state:      bd6e7c3df2b5779e0b61216e8b10b689 <-- AddRoundKey()

Round 10:
   state:      7a9f102789d5f50b2beffd9f3dca4ea7 <-- SubBytes()
   state:      7ad5fda789ef4e272bca100b3d9ff59f <-- ShiftRows()
   round key:  13111d7fe3944a17f307a78b4d2b30c5
   ciphertext: 69c4e0d86a7b0430d8cdb78070b4c55a <-- AddRoundKey()
 */
