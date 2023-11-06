package org.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InfoPage {

    public WebDriver driver;

    public InfoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='about_greeting']//*[@class='about_subtitle']")
    private WebElement infoText;
    @FindBy(xpath = "//*[@class='online_stat'][1]")
    private WebElement online;
    @FindBy(xpath = "//*[@class='online_stat'][2]")
    private WebElement inGame;
    @FindBy(xpath = "//*[@class='supernav_container']//a[1]")
    private WebElement magazine;

    public String getAboutText(){
        return infoText.getText();
    }

    public String getOnline() {
        return online.getText();
    }

    public String getInGame() {
        return inGame.getText();
    }

    public void clickMagazine() {
        magazine.click();
    }
}
