package org.example.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InitWebDriver {
    private static WebDriver driver;

    private InitWebDriver() {
    }

    public static WebDriver getWebDriver(ChromeOptions options) {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        return driver;
    }
}
