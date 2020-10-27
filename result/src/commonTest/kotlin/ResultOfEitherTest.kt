import tz.co.asoft.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ResultOfEitherTest {

    @Test
    fun `should serialize a result of either`() {
        val eit: Either<Car, Person> = Either.Right(Person("Andy"))
        val res: Result<Either<Car, Person>> = eit.asSuccess()
        println(res)
        assertEquals(res.status, ResultStatus.Success)
        println("Result Json: ${Result.stringifyEither(Car.serializer(), Person.serializer(), res)}")
    }

    @Test
    fun `should deserialize a person from json`() {
        val json = """{"name":"Andy"}"""
        val res = Result.parseEither(Car.serializer(), Person.serializer(), json)
        val either = res.value
        assertTrue(either is Either<*, *>)

        val person = either.value

        assertTrue(person is Person)
    }
}