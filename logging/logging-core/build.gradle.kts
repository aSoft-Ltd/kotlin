plugins {
    id("asoft-lib")
}

kotlin {
    js {
        browser {
            webpackTask {
                enabled = false
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(kotlin("stdlib-js"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("test"))
            }
        }
    }
}