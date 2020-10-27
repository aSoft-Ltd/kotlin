import kotlinx.serialization.builtins.serializer
import tz.co.asoft.*
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

class EitherTest {
    fun <L, R> print(e: Either<L, R>) = when (e) {
        is Either.Left -> println("E is left")
        is Either.Right -> println("E is right")
    }

    @Test
    fun `should have a better syntax`() {
        val thing: Either<Int, String> = Either.Left(1)
        print(thing)
        val thing2: Either<Int, String> = Either.Right("jaribio")
        print(thing2)
        assertFails {
            thing.right()
            thing2.left()
        }

        val fromThing2 = thing2.right()
        println("From Thing 2: $fromThing2")
        println("From Thing 2?: ${thing2.rightOrNull()}")
    }

    @Test
    fun `should serialize objects`() {
        val thing1: Either<Cat, Dog> = Either.Right(Dog("Stompy"))
        println(Either.stringify(Cat.serializer(), Dog.serializer(), thing1))

        val thing2: Either<String, Int> = Either.Right(1)
        println(Either.stringify(String.serializer(), Int.serializer(), thing2))

        val thing1Json = """{"name":"Stompy"}"""
        val fromThing1 = Either.parse(Cat.serializer(), Dog.serializer(), thing1Json)
        assertTrue(fromThing1.value is Cat)
    }
}