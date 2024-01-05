package org.example.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class MainPage(driver: WebDriver) {
    private var driver: WebDriver

    @FindBy(xpath = "//*[contains(text(), 'Популярное и рекомендуемое')]")
    private val homePageText: WebElement? = null

    @FindBy(xpath = "//*[@class='menuitem ' and contains(text(), 'Информация')]")
    private val homePageInformation: WebElement? = null

    @JvmField
    @FindBy(xpath = "//*[@class='pulldown_desktop' and text()='Новое и интересное']")
    val newAndInteresting: WebElement? = null

    @FindBy(xpath = "//*[@class='popup_menu_item' and contains(text(), 'Лидеры продаж')]")
    val topSell: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun getHomePageText(): String {
        return homePageText!!.text
    }

    fun clickInformation() {
        homePageInformation!!.click()
    }

    fun clickTopSell() {
        topSell!!.click()
    }
}