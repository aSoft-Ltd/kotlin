plugins {
    id("asoft-gradle-plugin")
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable:${versions.kotlin}")
}

gradlePlugin {
    plugins {
        val frontend by creating {
            id = "tz.co.asoft.frontend"
            implementationClass = "org.jetbrains.kotlin.gradle.frontend.FrontendPlugin"
        }
    }
}