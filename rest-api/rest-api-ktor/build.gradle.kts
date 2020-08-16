plugins {
    id("asoft-lib-jvm")
}

dependencies {
    api(project(":rest-api-core"))
    api(project(":result"))
    api(project(":persist"))
    api(project(":tools"))
    api(project(":logging"))
    api("io.ktor:ktor-server-cio:${versions.ktor}")
    api("io.ktor:ktor-network:${versions.ktor}")
}
