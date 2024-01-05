package org.example.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class TopSellingPage(driver: WebDriver) {
    private var driver: WebDriver

    @FindBy(xpath = "//*[text()='100 самых продаваемых игр на текущий момент по объёму выручки']")
    private val topSellingText: WebElement? = null

    @JvmField
    @FindBy(xpath = "//*[@type='button' and text()='Просмотреть больше лидеров продаж']")
    val buttonSeeMore: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    val topSellText: String
        get() = topSellingText!!.text

    fun clickSeeMore() {
        buttonSeeMore!!.click()
    }
}