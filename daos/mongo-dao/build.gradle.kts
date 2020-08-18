plugins {
    id("asoft-lib-jvm")
}

dependencies {
    api(project(":persist"))
    api(project(":tools"))
    api("org.mongodb:mongo-java-driver:${versions.mongo}")
}