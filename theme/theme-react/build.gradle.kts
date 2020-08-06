plugins {
    id("asoft-lib-browser-mpp")
}

kotlin.sourceSets["jsMain"].dependencies {
    api(project(":theme-core"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.kotlinx.coroutines}")
    api("org.jetbrains:kotlin-react:${versions.kotlinjs.react}")
}
