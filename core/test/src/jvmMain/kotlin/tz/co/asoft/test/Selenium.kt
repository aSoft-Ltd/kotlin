package tz.co.asoft.test

import kotlinx.coroutines.delay
import org.openqa.selenium.*

fun SearchContext.find(selector: String) = findElement(By.cssSelector(selector))!!
fun SearchContext.findAll(selector: String): List<WebElement> = findElements(By.cssSelector(selector))

fun SearchContext.find(by: By) = findElement(by)!!
fun SearchContext.findAll(by: By) = findElements(by)

fun SearchContext.findByAttr(name: String, value: String) = find(By.cssSelector("[$name='$value']"))

fun SearchContext.findSubmit() = findByAttr("type", "submit")

suspend fun WebDriver.waitUntil(predicate: () -> Boolean) {
    delay(500)
    try {
        if (!predicate()) throw Throwable("Condition is not met, waiting")
    } catch (c: Throwable) {
        waitUntil(predicate)
    }
}

fun WebDriver.deepClick(el: WebElement) {
    (this as JavascriptExecutor).executeScript("arguments[0].click()", el)
}

suspend fun WebDriver.scrollIntoView(el: WebElement) {
    (this as JavascriptExecutor).executeScript("arguments[0].scrollIntoView(true)", el)
    delay(1000)
}

fun WebElement.sendNewKeys(str: String) {
    clear()
    sendKeys(str)
}