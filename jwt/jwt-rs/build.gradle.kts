plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(project(":krypto"))
            api(project(":security-keys"))
            api(project(":jwt-core"))
        }
    }

    val commonTest by getting {
        dependencies {
            api(asoft("test"))
        }
    }
}
