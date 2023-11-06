package org.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchAllPage {

    public WebDriver driver;

    public SearchAllPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@class='pagesubheader' and text()='Все продукты']")
    private WebElement searchAllPageText;

    @FindBy(xpath = "//div[@data-loc='SteamOS + Linux']")
    private WebElement steamOSAndLinux;

    @FindBy(xpath = "//div[@data-loc='Кооператив (LAN)']")
    private WebElement coopLan;

    @FindBy(xpath = "//div[@data-loc='Экшен']")
    private WebElement action;

    @FindBy(xpath = "//div[@data-collapse-name='os']")
    private WebElement os;

    @FindBy(xpath = "//div[@data-collapse-name='category3']")
    private WebElement countPlayers;

    @FindBy(xpath = "//div[@data-collapse-name='tags']")
    private WebElement tags;

    @FindBy(xpath = "//div[@id='search_resultsRows']/child::a")
    private List<WebElement> foundedGames;

    public String getSearchAllPageText() {
        return searchAllPageText.getText();
    }

    public void enableSteamOSAndLinux() {
        String value = os.getAttribute("class");
        List<String> list = List.of(value.split(" "));
        boolean checkClassName = list.contains("collapsed");
        if (checkClassName) {
            os.click();
        }
        if (!steamOSAndLinux.isSelected()) {
            steamOSAndLinux.click();
        }
    }

    public boolean existSteamOSAndLinux() {
        return steamOSAndLinux.isSelected();
    }

    public void enableCoopLan() {
        String value = countPlayers.getAttribute("class");
        List<String> list = List.of(value.split(" "));
        boolean checkClassName = list.contains("collapsed");
        if (checkClassName) {
            countPlayers.click();
        }
        if (!coopLan.isSelected()) {
            coopLan.click();
        }
    }

    public boolean existCoopLan() {
        return coopLan.isSelected();
    }

    public void enableAction() {
        String value = tags.getAttribute("class");
        List<String> list = List.of(value.split(" "));
        boolean checkClassName = list.contains("collapsed");
        if (checkClassName) {
            tags.click();
        }
        if (!action.isSelected()) {
            action.click();
        }
    }

    public boolean existAction() {
        return action.isSelected();
    }

    public String getGamesInfo() {
        enableSteamOSAndLinux();
        enableCoopLan();
        enableAction();
        return foundedGames.get(0).getText();
    }
}
