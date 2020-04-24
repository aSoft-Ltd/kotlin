plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}

object versions {
    const val kotlin = "1.4-M1"
    const val jfrog = "1.8.5"

    object android {
        const val build_tools = "3.6.0"
    }
}

dependencies {
    api("com.android.tools.build:gradle:${versions.android.build_tools}")
    api("com.jfrog.bintray.gradle:gradle-bintray-plugin:${versions.jfrog}")
}