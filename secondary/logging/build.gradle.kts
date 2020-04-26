import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget

plugins {
    `asoft-lib`
}

val platformAttribute = Attribute.of("tz.co.asoft.logging.platform", String::class.java)
fun KotlinJsTarget.configureJs(platform: String) {
    attributes.attribute(platformAttribute, platform)
    compilations.all {
        kotlinOptions {
            metaInfo = true
            sourceMap = true
            moduleKind = "commonjs"
        }
    }
}

kotlin {
    android {
        attributes.attribute(platformAttribute, "android")
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
        publishLibraryVariants("release")
    }

    jvm {
        attributes.attribute(platformAttribute, "jvm")
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
    }

    js { configureJs("browser") }

    js("node") { configureJs("nodejs") }

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

        val jsCommonMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }

        val jsCommonTest by creating {
            dependsOn(jsCommonMain)
        }

        val jsMain by getting {
            dependsOn(jsCommonMain)
        }

        val jsTest by getting {
            dependsOn(jsCommonTest)
        }

        val nodeMain by getting {
            dependsOn(jsCommonMain)
        }

        val nodeTest by getting {
            dependsOn(jsCommonTest)
        }
    }
}