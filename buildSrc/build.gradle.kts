plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}

object versions {
    const val kotlin = "1.3.72"
    const val jfrog = "1.8.5"

    object android {
        const val build_tools = "3.6.0"
    }
}

dependencies {
    api("com.android.tools.build:gradle:${versions.android.build_tools}")
    api("com.jfrog.bintray.gradle:gradle-bintray-plugin:${versions.jfrog}")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    api("org.jetbrains.kotlin:kotlin-serialization:${versions.kotlin}")
}