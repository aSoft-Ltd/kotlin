plugins {
    kotlin("jvm")
    id("java")
    id("java-gradle-plugin")
    id("bintray-upload")
}

dependencies {
    api(gradleApi())
    api(localGroovy())

    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable:${versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${versions.kotlin}")

    testImplementation(gradleTestKit())
    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${versions.kotlin}")
}

gradlePlugin {
    plugins {
        val frontend by creating {
            id = "tz.co.asoft.frontend"
            implementationClass = "org.jetbrains.kotlin.gradle.frontend.FrontendPlugin"
        }
    }
}

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