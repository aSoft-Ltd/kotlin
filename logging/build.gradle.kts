plugins {
    id("root-module")
    id("asoft-lib")
}

kotlin.js {
    useCommonJs()
    browser {}
}