plugins {
    `asoft-lib`
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api(project(":primary:klock"))
            api(project(":primary:krypto"))
            api(project(":primary:persist"))
            api(project(":primary:rx"))
            api(project(":primary:phone"))
            api(project(":primary:email"))
            api(project(":primary:io"))
            api(project(":secondary:neo4j"))
            api(project(":secondary:storage"))
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