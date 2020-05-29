plugins {
    id("maven-publish")
    id("com.jfrog.bintray")
}

bintray {
    configureBintray()
    setPublications("pluginMaven","${project.name}PluginMarkerMaven")
}