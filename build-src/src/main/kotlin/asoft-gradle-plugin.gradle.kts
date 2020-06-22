plugins {
    id("space-maven")
    kotlin("jvm")
    id("java")
    id("java-gradle-plugin")
}

repositories {
    repos()
}

group = "tz.co.asoft"
version = versions.asoft

defaultTasks("jar")

val sourcesJar by tasks.creating(org.gradle.jvm.tasks.Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.creating(org.gradle.jvm.tasks.Jar::class) {
    archiveClassifier.value("javadoc")
}

artifacts {
    archives(sourcesJar)
    archives(javadocJar)
}

dependencies {
    api(gradleApi())
    api(localGroovy())

    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable:${versions.kotlin}")

    testImplementation(gradleTestKit())
    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${versions.kotlin}")
}
