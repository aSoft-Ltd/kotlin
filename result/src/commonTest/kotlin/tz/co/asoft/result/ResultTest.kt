package tz.co.asoft.result

import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.nullable
import kotlin.test.Test

class ResultTest {
    @Test
    fun can_serialize_null_data() {
        val result1 = Result.Success(Person("Andy"))
        println(Result.stringify(Person.serializer(), result1))
        val result2 = Result.Success<Person?>(null)
        println(Result.stringify(Person.serializer().nullable, result2))
    }

    @Test
    fun can_serialize_a_list_of_people() {
        val result: Result<List<Person>> = Result.Success(listOf(Person("Andy")))
        println(Result.stringify(Person.serializer().list, result))
    }

    @Test
    fun can_deserialize_data() {
        val listOfPeople = """{"data":[{"name":"Andy"}]}"""
        println(Result.parse(Person.serializer().list, listOfPeople))

        val person = """{"data":{"name":"Andy"}}"""
        println(Result.parse(Person.serializer().nullable, person))

        val nullPerson = """{"data":null}"""
        println(Result.parse(Person.serializer().nullable, nullPerson))

        val failedPerson = """{"data":1}"""
        println(Result.parse(Person.serializer().nullable, failedPerson))
    }
}