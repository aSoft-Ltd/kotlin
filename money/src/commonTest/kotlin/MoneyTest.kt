import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import tz.co.asoft.Money
import tz.co.asoft.TZS
import tz.co.asoft.USD
import kotlin.test.Test
import kotlin.test.assertEquals

class MoneyTest {
    @Test
    fun `should store cents`() {
        assertEquals(200000, 2000.TZS.value)
    }

    @Test
    fun `dollors are stored the same way as tzs`() {
        assertEquals(2.USD.value, 200)
        assertEquals(0.76.USD.value, 76)
        assertEquals(2.989.USD.value, 298)
    }

    @Test
    fun `should display a nice string`() {
        assertEquals("2,000", 2_000.TZS.toString())
        assertEquals("TZS 3,500", 3500.TZS.toFullString())
        assertEquals("USD 0.98", 0.98.USD.toFullString())
        assertEquals("TZS 1,000", (2000.TZS / 2).toFullString())
        assertEquals("USD 2", (1.USD * 2).toFullString())
        assertEquals("TZS 1", (1.TZS * 1).toFullString())
        assertEquals("TZS 1", (1.TZS / 1).toFullString())
        assertEquals("TZS 0.5", 0.5.TZS.toFullString())
        assertEquals("TZS 0.5", (1.TZS / 2).toFullString())
    }

    @Test
    fun `trancation should work`() {
        assertEquals("USD 2.98", 2.989.USD.toFullString())
    }

    @Test
    fun `serializes well`() {
        val money = 2000.TZS
        val json = Json(configuration = JsonConfiguration(isLenient = true))
        println(json.stringify(Money.serializer(), money))
        val moneyFromString = json.parse(Money.serializer(), "200000 cents @ 100 cents per TZS")
        assertEquals(money, moneyFromString)
        assertEquals(money.cur, moneyFromString.cur)
    }
}