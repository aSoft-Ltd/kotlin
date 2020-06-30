pluginManagement {
    repositories {
        google()
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if(requested.id.id.startsWith("org.jetbrains.kotlin")) {
//                useModule()
            }
        }
    }
}