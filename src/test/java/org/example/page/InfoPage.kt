package org.example.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class InfoPage(driver: WebDriver) {
    private var driver: WebDriver

    @FindBy(xpath = "//*[@id='about_greeting']//*[@class='about_subtitle']")
    private val infoText: WebElement? = null

    @FindBy(xpath = "//*[@class='online_stat'][1]")
    private val online: WebElement? = null

    @FindBy(xpath = "//*[@class='online_stat'][2]")
    private val inGame: WebElement? = null

    @FindBy(xpath = "//*[@class='supernav_container']//a[1]")
    private val magazine: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    val aboutText: String
        get() = infoText!!.text

    fun getOnline(): String {
        return online!!.text
    }

    fun getInGame(): String {
        return inGame!!.text
    }

    fun clickMagazine() {
        magazine!!.click()
    }
}