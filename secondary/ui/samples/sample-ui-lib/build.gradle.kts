plugins {
    kotlin("multiplatform")
}

kotlin {
    js {
        useCommonJs()
        browser {}
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":secondary:ui"))
            }
        }

        val jsMain by getting {

        }
    }
}