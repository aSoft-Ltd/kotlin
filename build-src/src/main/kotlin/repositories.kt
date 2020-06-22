import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

fun RepositoryHandler.repos() {
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    mavenCentral()
}