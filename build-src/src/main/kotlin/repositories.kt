import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.repos() {
    google()
    jcenter()
    mavenCentral()
}