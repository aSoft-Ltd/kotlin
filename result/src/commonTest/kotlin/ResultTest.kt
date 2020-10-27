import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.nullable
import tz.co.asoft.*
import kotlin.test.Test

class ResultTest {
    @Test
    fun can_serialize_null_data() {
        val result1 = Success(Person("Andy"))
        println(Result.stringify(Person.serializer(), result1))
        val result2 = Success<Person?>(null)
        println(Result.stringify(Person.serializer().nullable, result2))
    }

    @Test
    fun can_serialize_a_list_of_people() {
        val result: Result<List<Person>> = Success(
            listOf(Person("Andy"))
        )
        println(Result.stringify(Person.serializer().list, result))
    }

    @Test
    fun can_deserialize_data() {
        val listOfPeople = """[{"name":"Andy"}]"""
        println(Result.parse(Person.serializer().list, listOfPeople))

        val person = """{"name":"Andy"}"""
        println(Result.parse(Person.serializer().nullable, person))

        val nullPerson = """{null}"""
        println(Result.parse(Person.serializer().nullable, nullPerson))

        val failedPerson = """{1}"""
        println(Result.parse(Person.serializer().nullable, failedPerson))
    }
}