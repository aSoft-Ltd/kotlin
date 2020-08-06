import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

fun RepositoryHandler.repos() {
    mavenLocal()
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    maven(url = "https://maven.jetbrains.space/asofttz/kotlin") {
      credentials {
        username = System.getenv("SPACE_USERNAME")
        password = System.getenv("SPACE_PASSWORD")
      }
    }
    mavenCentral()
}
