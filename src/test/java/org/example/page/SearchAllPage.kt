package org.example.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class SearchAllPage(driver: WebDriver) {
    private var driver: WebDriver

    @FindBy(xpath = "//*[@class='pagesubheader' and text()='Все продукты']")
    private val searchAllPageText: WebElement? = null

    @FindBy(xpath = "//div[@data-loc='SteamOS + Linux']")
    private val steamOSAndLinux: WebElement? = null

    @FindBy(xpath = "//div[@data-loc='Кооператив (LAN)']")
    private val coopLan: WebElement? = null

    @FindBy(xpath = "//div[@data-loc='Экшен']")
    private val action: WebElement? = null

    @FindBy(xpath = "//div[@data-collapse-name='os']")
    private val os: WebElement? = null

    @FindBy(xpath = "//div[@data-collapse-name='category3']")
    private val countPlayers: WebElement? = null

    @FindBy(xpath = "//div[@data-collapse-name='tags']")
    private val tags: WebElement? = null

    @FindBy(xpath = "//div[@id='search_resultsRows']/child::a")
    private val foundedGames: List<WebElement>? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun getSearchAllPageText(): String {
        return searchAllPageText!!.text
    }

    fun enableSteamOSAndLinux() {
        val value = os!!.getAttribute("class")
        val list = java.util.List.of(*value.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        val checkClassName = list.contains("collapsed")
        if (checkClassName) {
            os.click()
        }
        if (!steamOSAndLinux!!.isSelected) {
            steamOSAndLinux.click()
        }
    }

    fun existSteamOSAndLinux(): Boolean {
        return steamOSAndLinux!!.isEnabled
    }

    fun enableCoopLan() {
        val value = countPlayers!!.getAttribute("class")
        val list = java.util.List.of(*value.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        val checkClassName = list.contains("collapsed")
        if (checkClassName) {
            countPlayers.click()
        }
        if (!coopLan!!.isSelected) {
            coopLan.click()
        }
    }

    fun existCoopLan(): Boolean {
        return coopLan!!.isEnabled
    }

    fun enableAction() {
        val value = tags!!.getAttribute("class")
        val list = java.util.List.of(*value.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        val checkClassName = list.contains("collapsed")
        if (checkClassName) {
            tags.click()
        }
        if (!action!!.isSelected) {
            action.click()
        }
    }

    fun existAction(): Boolean {
        return action!!.isEnabled
    }

    val gamesInfo: String
        get() {
            enableSteamOSAndLinux()
            enableCoopLan()
            enableAction()
            return foundedGames!![0].text
        }
}