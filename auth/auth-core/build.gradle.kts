plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api(project(":klock"))
            api(project(":krypto"))
            api(project(":persist"))
            api(project(":phone"))
            api(project(":email"))
            api(project(":io"))
            api(project(":viewmodel"))
            api(project(":tools"))
            api(project(":neo4j"))
            api(project(":storage"))
        }
    }

    val commonTest by getting {
        dependencies {
            implementation(asoft("test"))
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