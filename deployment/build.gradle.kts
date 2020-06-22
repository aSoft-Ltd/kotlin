plugins {
    id("asoft-gradle-plugin")
}

dependencies {
    api("com.android.tools.build:gradle:${versions.android.build_tools}")
    api(asoft("frontend"))
}

gradlePlugin {
    plugins {
        val deployment by creating {
            id = "tz.co.asoft.deployment"
            implementationClass = "tz.co.asoft.deployment.plugins.DeploymentPlugin"
        }
    }
}