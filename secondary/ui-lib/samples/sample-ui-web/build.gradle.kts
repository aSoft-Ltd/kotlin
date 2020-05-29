import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
}

kotlin {
    js {
        useCommonJs()
        browser {
            webpackTask {

            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":secondary:ui:samples:sample-ui-lib"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(npm("file-loader", "*"))
                api(npm("style-loader", "*"))
                api(npm("css-loader", "*"))
                api(npm("less", "*"))
                api(npm("less-loader", "*"))
                api(npm("babel-loader", "8"))

                api(npm("@babel/core", "*"))
                api(npm("@babel/preset-env", "*"))
                api(npm("@babel/preset-react", "*"))
            }
        }
    }
}