package org.example.util

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

object InitWebDriver {
    private var driver: WebDriver? = null
    fun getWebDriver(options: ChromeOptions?): WebDriver? {
        if (driver == null) {
            WebDriverManager.chromedriver().setup()
            driver = ChromeDriver(options)
        }
        return driver
    }
}