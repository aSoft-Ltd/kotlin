plugins {
    kotlin("jvm")
    id("java")
    id("java-gradle-plugin")
    id("bintray-upload")
}

dependencies {
    api(gradleApi())
    api(localGroovy())

    api("com.android.tools.build:gradle:${versions.android.build_tools}")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    api(project(":gradle-plugins:frontend"))

    testImplementation(gradleTestKit())
    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${versions.kotlin}")
}

gradlePlugin {
    plugins {
        val deployment by creating {
            id = "tz.co.asoft.deployment"
            implementationClass = "tz.co.asoft.deployment.plugins.DeploymentPlugin"
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