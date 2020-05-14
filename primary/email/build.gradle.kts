plugins {
    `asoft-lib`
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
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

tasks.create("comps") {
    doFirst {
        kotlin.targets.onEach { target ->
            target.compilations.all {
                println("${target.name}-$name")
            }
        }
    }
}

tasks.create("jars") {
    doFirst {
        kotlin.targets.onEach { target ->
            target.compilations.getByName("name").compileKotlinTask
        }
    }
}