plugins {
    id("asoft-lib")
}

android {
    defaultConfig {
        minSdkVersion(17)
    }
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api(project(":persist"))
        }
    }
}