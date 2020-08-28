plugins {
    kotlin("js")
}

repositories {
    repos()
}

kotlin {
    target {
        useCommonJs()
    }
}