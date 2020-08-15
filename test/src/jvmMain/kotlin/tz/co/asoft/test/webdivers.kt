package tz.co.asoft.test

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.opera.OperaDriver
import org.openqa.selenium.opera.OperaOptions

fun webDrivers(): List<WebDriver> {
    val drivers = mutableListOf<WebDriver>()

    System.setProperty("webdriver.opera.driver", "/home/andylamax/Downloads/operadriver_linux64/operadriver")
    drivers += OperaDriver(OperaOptions().apply {
        setCapability("browserName", "chrome")
        setCapability("browserVersion", "62.0")
    })

    System.setProperty("webdriver.chrome.driver", "/home/andylamax/Downloads/chromedriver")
    drivers += ChromeDriver()
    return drivers
}