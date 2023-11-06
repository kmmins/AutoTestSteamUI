package org.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopSellingPage {

    public WebDriver driver;

    public TopSellingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[text()='100 самых продаваемых игр на текущий момент по объёму выручки']")
    private WebElement topSellingText;

    @FindBy(xpath = "//*[@type='button' and text()='Просмотреть больше лидеров продаж']")
    private WebElement buttonSeeMore;

    public String getTopSellText(){
        return topSellingText.getText();
    }

    public WebElement getButtonSeeMore(){
        return buttonSeeMore;
    }

    public void clickSeeMore() {
        buttonSeeMore.click();
    }
}
