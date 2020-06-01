plugins {
    `asoft-lib`
}

kotlin.sourceSets {
    val commonMain by getting {
        dependencies {
            implementation(kotlin("stdlib-common"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${versions.kotlinx.coroutines}")
            api(kotlin("test-common"))
            api(kotlin("test-annotations-common"))
        }
    }

    val androidMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api(kotlin("test"))
            api(kotlin("test-junit"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlinx.coroutines}")
            api("androidx.test.espresso:espresso-core:${versions.androidx.espresso}")
            api("androidx.test:runner:${versions.androidx.test_runner}")
            api("androidx.test:rules:${versions.androidx.test_rules}")
        }
    }

    val jvmMain by getting {
        dependencies {
            implementation(kotlin("stdlib"))
            api(kotlin("test"))
            api(kotlin("test-junit"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx.coroutines}")
            api("org.seleniumhq.selenium:selenium-java:${versions.selenium}")
        }
    }

    val jsMain by getting {
        dependencies {
            implementation(kotlin("stdlib-js"))
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
            api(kotlin("test-js"))
        }
    }
}