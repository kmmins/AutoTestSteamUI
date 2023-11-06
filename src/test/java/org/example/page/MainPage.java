package org.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(text(), 'Популярное и рекомендуемое')]")
    private WebElement homePageText;

    @FindBy(xpath = "//*[@class='menuitem ' and contains(text(), 'Информация')]")
    private WebElement homePageInformation;

    @FindBy(xpath = "//*[@class='pulldown_desktop' and text()='Новое и интересное']")
    private WebElement newAndInteresting;

    @FindBy(xpath = "//*[@class='popup_menu_item' and contains(text(), 'Лидеры продаж')]")
    private WebElement topSell;

    public String getHomePageText() {
        return homePageText.getText();
    }

    public void clickInformation() {
        homePageInformation.click();
    }

    public WebElement getNewAndInteresting() {
        return newAndInteresting;
    }

    public WebElement getTopSell() {
        return topSell;
    }

    public void clickTopSell() {
        topSell.click();
    }
}

