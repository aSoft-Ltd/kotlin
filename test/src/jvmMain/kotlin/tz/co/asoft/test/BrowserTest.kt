package tz.co.asoft.test

import kotlinx.coroutines.async
import org.openqa.selenium.WebDriver

open class BrowserTest(
        val url: String = "http://localhost:8088",
        val drivers: List<WebDriver> = webDrivers()
) : AsyncTest() {
    init {
        drivers.forEach {
            it.manage().window().fullscreen()
        }
    }


    fun WebDriver.visit() = get(url)

    fun browserTest(body: suspend WebDriver.() -> Unit) = asyncTest {
        drivers.map {
            async {
                println("Running with ${it.javaClass.name}")
                it.visit()
                it.body()
            }
        }.forEach { it.await() }
    }
}