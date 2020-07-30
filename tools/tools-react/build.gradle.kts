plugins {
    id("asoft-lib")
}

kotlin.sourceSets {
    val jsMain by getting {
        dependencies {
            api(project(":tools-core"))
            api("org.jetbrains:kotlin-react:${versions.kotlinjs.react}")
            api("org.jetbrains:kotlin-styled:${versions.kotlinjs.styled}")
            api("org.jetbrains:kotlin-react-router-dom:${versions.kotlinjs.reactRouterDom}")
        }
    }
}
