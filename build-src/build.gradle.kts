plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    mavenCentral()
}

object versions {
    const val kotlin = "1.3.72"

    object android {
        const val build_tools = "4.0.0" //"4.0.0"
    }
}

dependencies {
    api("com.android.tools.build:gradle:${versions.android.build_tools}")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    api("org.jetbrains.kotlin:kotlin-serialization:${versions.kotlin}")
}