plugins {
    id("asoft-lib-jvm")
}

dependencies {
    api(project(":persist"))
    api("org.neo4j:neo4j-ogm-core:${versions.neo4j}")
    api("org.neo4j:neo4j-ogm-api:${versions.neo4j}")
    api("org.neo4j:neo4j-ogm-http-driver:${versions.neo4j}")
    testApi(asoft("test"))
}