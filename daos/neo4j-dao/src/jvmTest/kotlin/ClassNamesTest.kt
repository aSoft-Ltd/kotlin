import org.junit.Test
import tz.co.test.Outer

class ClassNamesTest {

    @Test
    fun `print class names`() {
        val c1 = Outer()
        val c2 = Outer.Inner()
        val c3 = Outer.Inner.MostInner()

        listOf(c1, c2, c3).forEach {
            printClassName(it)
            println()
        }
    }

    private fun printClassName(obj: Any) {
        println("Simple Name: ${obj::class.java.simpleName}")
        println("Canonical Name: ${obj::class.java.canonicalName}")
        println("Name: ${obj::class.java.name}")
        println("Package: ${obj::class.java.`package`.name}")
    }
}