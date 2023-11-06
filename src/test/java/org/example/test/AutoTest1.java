package org.example.test;

import org.example.page.InfoPage;
import org.example.page.MainPage;
import org.example.page.SearchAllPage;
import org.example.page.TopSellingPage;
import org.example.util.InitWebDriver;
import org.example.util.ReadProperties;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutoTest1 {

    public static WebDriver chromeDriver;
    public static MainPage mainPage;
    public static InfoPage infoPage;
    public static TopSellingPage topSellingPage;
    public static SearchAllPage searchAllPage;

    @BeforeAll
    public static void setup() throws IOException {
        System.setProperty("webdriver.chromedriver", ReadProperties.get("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--incognito");
        chromeDriver = InitWebDriver.getWebDriver(options);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        chromeDriver.get(ReadProperties.get("baseURL"));
        mainPage = new MainPage(chromeDriver);
        infoPage = new InfoPage(chromeDriver);
        topSellingPage = new TopSellingPage(chromeDriver);
        searchAllPage = new SearchAllPage(chromeDriver);
    }

    @Test
    @Order(1)
    public void testCase1Step1() {
        String mainPageText = mainPage.getHomePageText();

        assertEquals("Популярное и рекомендуемое".toUpperCase(), mainPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.");
    }

    @Test
    @Order(2)
    public void testCase1Step2() {
        mainPage.clickInformation();
        String infoPageText = infoPage.getAboutText();

        assertEquals("Steam — превосходная платформа для игроков и разработчиков.", infoPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.");
    }

    @Test
    @Order(3)
    public void testCase1Step3() {
        String online = infoPage.getOnline();
        String inGame = infoPage.getInGame();
        String onlineAfter = online.replaceAll("[^0-9]", "");
        String inGameAfter = inGame.replaceAll("[^0-9]", "");
        boolean compareUsers = Integer.parseInt(onlineAfter) >= Integer.parseInt(inGameAfter);

        assertTrue(compareUsers, "Ошибка, онлайн не может быть меньше чем число игроков в игре.");
    }

    @Test
    @Order(4)
    public void testCase1Step4() {
        infoPage.clickMagazine();
        String mainPageTextAfter = mainPage.getHomePageText();

        assertEquals("Популярное и рекомендуемое".toUpperCase(), mainPageTextAfter,
                "Ошибка, текст на странице не соответствует ожидаемому.");
    }

    @Test
    @Order(5)
    public void testCase2Step1() {
        String mainPageText = mainPage.getHomePageText();

        assertEquals("Популярное и рекомендуемое".toUpperCase(), mainPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.");
    }

    @Test
    @Order(6)
    public void testCase2Step2() {
        new Actions(chromeDriver)
                .moveToElement(mainPage.getNewAndInteresting())
                .perform();
        //как использовать явные ожидания?
        //WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.invisibilityOf(mainPage.getTopSell()));
        mainPage.clickTopSell();
        String topSellingPageText = topSellingPage.getTopSellText();

        assertEquals("100 самых продаваемых игр на текущий момент по объёму выручки", topSellingPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.");
    }

    @Test
    @Order(7)
    public void testCase2Step3() {
        new Actions(chromeDriver)
                .scrollToElement(topSellingPage.getButtonSeeMore())
                .perform();
        topSellingPage.clickSeeMore();
        String searchAllPageText = searchAllPage.getSearchAllPageText();

        assertEquals("Все продукты", searchAllPageText,
                "Ошибка, текст на странице не соответствует ожидаемому.");
    }

    @Test
    @Order(8)
    public void testCase2Step4() {
        boolean before = searchAllPage.existSteamOSAndLinux();
        System.out.println("ос до: "+ before);

        searchAllPage.enableSteamOSAndLinux();
        boolean after = searchAllPage.existSteamOSAndLinux();
        System.out.println("ос после: "+ after);

        assertTrue(after, "Ошибка, чекбокс не включен.");

    }

    @Test
    @Order(9)
    public void testCase2Step5() {
        boolean before = searchAllPage.existCoopLan();
        System.out.println("кооп до: "+ before);

        searchAllPage.enableCoopLan();
        boolean after = searchAllPage.existCoopLan();
        System.out.println("кооп после: "+ after);

        assertTrue(after, "Ошибка, чекбокс не включен.");
    }

    @Test
    @Order(10)
    public void testCase2Step6() {
        boolean a = searchAllPage.existAction();
        System.out.println("экшн до: "+ a);


        searchAllPage.enableAction();
        boolean check = searchAllPage.existAction();
        System.out.println("экшн после: "+ check);

        assertTrue(check, "Ошибка, чекбокс не включен.");

    }

    @Test
    @Order(11)
    public void testCase2Step7() {
        System.out.println(searchAllPage.getGamesInfo());
    }

    @AfterAll
    public static void end() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}
