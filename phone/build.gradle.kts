plugins {
    id("asoft-lib")
	id("root-module")
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            api(kotlin("stdlib-common"))
        }
    }

    val commonTest by getting {
        dependencies {
            api(asoft("test"))
        }
    }

    val androidMain by getting {
        dependencies {
            api(kotlin("stdlib"))
        }
    }

    val jvmMain by getting {
        dependencies {
            api(kotlin("stdlib"))
        }
    }

    val jsMain by getting {
        dependencies {
            api(kotlin("stdlib-js"))
        }
    }
}
