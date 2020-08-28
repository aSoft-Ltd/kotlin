plugins {
    id("sample-js-only")
}

kotlin {
    target {
        useCommonJs()
        browser {
            webpackTask {
                devServer = devServer?.copy(open = false)
            }
        }
    }
}
dependencies {
    implementation(project(":logging-core"))
}