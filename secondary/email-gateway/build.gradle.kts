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
            api(project(":primary:email"))
            api(project(":primary:persist"))
            api(project(":primary:io"))
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
        }
    }

    val jsMain by getting {
        dependencies {
            implementation(kotlin("stdlib-js"))
        }
    }
}