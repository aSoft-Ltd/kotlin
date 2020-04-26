import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    `asoft-lib`
}

android {
    defaultConfig {
        minSdkVersion(9)
    }
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api(project(":primary:persist"))
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(project(":primary:test"))
        }
    }

    val androidMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
        }
    }

    val jvmMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api("org.neo4j:neo4j-ogm-core:${versions.neo4j}")
            api("org.neo4j:neo4j-ogm-api:${versions.neo4j}")
            api("org.neo4j:neo4j-ogm-http-driver:${versions.neo4j}")
        }
    }

    val jsMain by getting {
        dependencies {
            implementation(kotlin("stdlib-js"))
        }
    }
}