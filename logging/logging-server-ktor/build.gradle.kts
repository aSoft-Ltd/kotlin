plugins {
    id("asoft-lib-jvm")
    application
}

application{
    mainClassName = "tz.co.asoft.MainKt"
}

kotlin.sourceSets {
    val main by getting {
        dependencies {
            api("io.ktor:ktor-server-cio:${versions.ktor}")
            api(project(":logging-console"))
            api(project(":logging-file"))
            api(project(":logging-rest"))
        }
    }
}