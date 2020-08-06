plugins {
    id("asoft-lib-browser")
}

kotlin.sourceSets["main"].dependencies {
    api(project(":paging-core"))
    api(project(":react-tables"))
    api(project(":react-feedback"))
}
