plugins {
    `asoft-lib`
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api(project(":secondary:networking"))
            api(project(":secondary:email-gateway"))
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