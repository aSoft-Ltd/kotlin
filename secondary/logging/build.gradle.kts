plugins {
    `asoft-lib`
}

kotlin {
    android {
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
        publishLibraryVariants("release")
    }

    jvm {
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
    }

    js { useCommonJs() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api(project(":primary:klock"))
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
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
    }
}