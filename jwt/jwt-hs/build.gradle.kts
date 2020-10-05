plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":jwt-core"))
        }
    }

    val commonTest by getting {
        dependencies {
            api(asoft("test"))
        }
    }
}
