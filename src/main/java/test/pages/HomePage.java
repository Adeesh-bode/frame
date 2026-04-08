package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "welcomeMessage")
    WebElement welcomeMessage;

    @FindBy(id = "searchBox")
    WebElement searchBox;

    @FindBy(id = "searchButton")
    WebElement searchButton;

    @FindBy(linkText = "Profile")
    WebElement profileLink;

    public boolean isWelcomeMessageDisplayed() {
        return welcomeMessage.isDisplayed();
    }

    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public void search(String keyword) {
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchButton.click();
    }

    public void navigateToProfile() {
        profileLink.click();
    }
}