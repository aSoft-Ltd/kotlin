plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val jvmMain by getting {
        dependencies {
            api(project(":rest-controller-core"))
            api(project(":result"))
			api(project(":persist"))
			api(project(":tools-core"))
            api("io.ktor:ktor-server-cio:${versions.ktor}")
            api("io.ktor:ktor-network:${versions.ktor}")
        }
    }
}
