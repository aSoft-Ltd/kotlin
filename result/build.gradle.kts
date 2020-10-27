plugins {
    id("asoft-lib")
    id("root-module")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":either"))
        }
    }

    val commonTest by getting {
        dependencies {
            api(asoft("test"))
        }
    }
}