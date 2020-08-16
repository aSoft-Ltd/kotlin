import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    id("asoft-lib")
}

android {
    defaultConfig {
        minSdkVersion(9)
    }
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":persist"))
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(asoft("test"))
        }
    }

    val jvmMain by getting {
        dependencies {
            api("org.neo4j:neo4j-ogm-core:${versions.neo4j}")
            api("org.neo4j:neo4j-ogm-api:${versions.neo4j}")
            api("org.neo4j:neo4j-ogm-http-driver:${versions.neo4j}")
        }
    }
}