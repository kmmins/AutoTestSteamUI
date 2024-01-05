package org.example.test

import org.example.page.InfoPage
import org.example.page.MainPage
import org.example.page.SearchAllPage
import org.example.page.TopSellingPage
import org.example.util.InitWebDriver
import org.example.util.ReadProperties
import org.junit.jupiter.api.*
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions
import java.io.IOException
import java.time.Duration
import java.util.*


class AutoTest1 {
    @Test
    @Order(1)
    fun testCase1Step1() {
        val mainPageText = mainPage!!.getHomePageText()
        Assertions.assertEquals("Популярное и рекомендуемое".uppercase(Locale.getDefault()), mainPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.")
    }

    @Test
    @Order(2)
    fun testCase1Step2() {
        mainPage!!.clickInformation()
        val infoPageText = infoPage!!.aboutText
        Assertions.assertEquals("Steam — превосходная платформа для игроков и разработчиков.", infoPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.")
    }

    @Test
    @Order(3)
    fun testCase1Step3() {
        val online = infoPage!!.getOnline()
        val inGame = infoPage!!.getInGame()
        val onlineAfter = online.replace("[^0-9]".toRegex(), "")
        val inGameAfter = inGame.replace("[^0-9]".toRegex(), "")
        val compareUsers = onlineAfter.toInt() >= inGameAfter.toInt()
        Assertions.assertTrue(compareUsers, "Ошибка, онлайн не может быть меньше чем число игроков в игре.")
    }

    @Test
    @Order(4)
    fun testCase1Step4() {
        infoPage!!.clickMagazine()
        val mainPageTextAfter = mainPage!!.getHomePageText()
        Assertions.assertEquals("Популярное и рекомендуемое".uppercase(Locale.getDefault()), mainPageTextAfter,
                "Ошибка, текст на странице не соответствует ожидаемому.")
    }

    @Test
    @Order(5)
    fun testCase2Step1() {
        val mainPageText = mainPage!!.getHomePageText()
        Assertions.assertEquals("Популярное и рекомендуемое".uppercase(Locale.getDefault()), mainPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.")
    }

    @Test
    @Order(6)
    fun testCase2Step2() {
        Actions(chromeDriver)
                .moveToElement(mainPage!!.newAndInteresting)
                .perform()
        mainPage!!.clickTopSell()
        val topSellingPageText = topSellingPage!!.topSellText
        Assertions.assertEquals("100 самых продаваемых игр на текущий момент по объёму выручки", topSellingPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.")
    }

    @Test
    @Order(7)
    fun testCase2Step3() {
        Actions(chromeDriver)
                .scrollToElement(topSellingPage!!.buttonSeeMore)
                .perform()
        topSellingPage!!.clickSeeMore()
        val searchAllPageText = searchAllPage!!.getSearchAllPageText()
        Assertions.assertEquals("Все продукты", searchAllPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.")
    }

    @Test
    @Order(8)
    fun testCase2Step4() {
        searchAllPage!!.enableSteamOSAndLinux()
        val after = searchAllPage!!.existSteamOSAndLinux()
        Assertions.assertTrue(after, "Ошибка, чекбокс не включен.")
    }

    @Test
    @Order(9)
    fun testCase2Step5() {
        searchAllPage!!.enableCoopLan()
        val after = searchAllPage!!.existCoopLan()
        Assertions.assertTrue(after, "Ошибка, чекбокс не включен.")
    }

    @Test
    @Order(10)
    fun testCase2Step6() {
        searchAllPage!!.enableAction()
        val check = searchAllPage!!.existAction()
        Assertions.assertTrue(check, "Ошибка, чекбокс не включен.")
    }

    @Test
    @Order(11)
    fun testCase2Step7() {
        println(searchAllPage!!.gamesInfo)
    }

    companion object {
        var chromeDriver: WebDriver? = null
        var mainPage: MainPage? = null
        var infoPage: InfoPage? = null
        var topSellingPage: TopSellingPage? = null
        var searchAllPage: SearchAllPage? = null

        @JvmStatic
        @BeforeAll
        @Throws(IOException::class)
        fun setup() {
            System.setProperty("webdriver.chromedriver", ReadProperties["chromedriver"])
            val options = ChromeOptions()
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL)
            options.addArguments("--incognito")
            chromeDriver = InitWebDriver.getWebDriver(options)
            chromeDriver?.manage()?.window()?.maximize()
            chromeDriver?.manage()?.timeouts()?.implicitlyWait(Duration.ofSeconds(10))
            chromeDriver?.get(ReadProperties["baseURL"])
            mainPage = chromeDriver?.let { MainPage(it) }
            infoPage = chromeDriver?.let { InfoPage(it) }
            topSellingPage = chromeDriver?.let { TopSellingPage(it) }
            searchAllPage = chromeDriver?.let { SearchAllPage(it) }
        }

        @JvmStatic
        @AfterAll
        fun end() {
            chromeDriver!!.quit()
            chromeDriver = null
        }
    }
}