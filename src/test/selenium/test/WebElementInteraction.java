package com.epam.campus.selenium;

import com.epam.campus.selenium.Factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebElementInteraction {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = "https://demoqa.com";
    private static final int TIMEOUT_SECONDS = 10;
    private static final int IMPLICIT_WAIT_SECONDS = 5;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.createDriver(browser);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));

        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        System.out.println("Browser initialized successfully.");
    }

    @Test
    public void testButton() {
        Actions actions = new Actions(driver);

        driver.get(BASE_URL + "/buttons");

        // Dynamic Click Button
        WebElement clickMeButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
        clickMeButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicClickMessage")));
        WebElement clickBtnMessage = driver.findElement(By.id("dynamicClickMessage"));

        Assert.assertTrue(
                clickBtnMessage.isDisplayed(),
                "Dynamic click message is not displayed"
        );

        // Double Click Button
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(doubleClickBtn).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doubleClickMessage")));
        WebElement doubleClickBtnMessage = driver.findElement(By.id("doubleClickMessage"));

        Assert.assertTrue(
                doubleClickBtnMessage.isDisplayed(),
                "Double click message is not displayed"
        );

        // Right Click Button
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClickBtn).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightClickMessage")));
        WebElement rightClickBtnMessage = driver.findElement(By.id("rightClickMessage"));

        Assert.assertTrue(
                rightClickBtnMessage.isDisplayed(),
                "Right click message is not displayed"
        );
    }

    @Test
    public void testText(){
        driver.get(BASE_URL + "/text-box");

        WebElement fullName = driver.findElement(By.id("userName"));
        WebElement email = driver.findElement(By.id("userEmail"));

        String expectedName = "John Doe";
        String expectedEmail = "john@example.com";

        fullName.sendKeys(expectedName);
        email.sendKeys(expectedEmail);

        WebElement submit = driver.findElement(By.id("submit"));
        /*
        The demoqa.com site has a sticky footer that sometimes overlaps the submit button, especially on smaller windows or after zooming/scaling.
         */
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        submit.click();

        // Assert submitted output
        WebElement nameOutput = driver.findElement(By.id("name"));
        WebElement emailOutput = driver.findElement(By.id("email"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));


        Assert.assertTrue(nameOutput.getText().contains(expectedName));
        Assert.assertTrue(emailOutput.getText().contains(expectedEmail));

        fullName.clear();
        email.clear();

    }

    @Test
    public void dropDownSelectionTest() {
        driver.get( BASE_URL + "/select-menu");


        // Muktiple select
        WebElement multiSelect = driver.findElement(By.id("cars"));

        Select selectMulti = new Select(multiSelect);
        selectMulti.selectByValue("volvo"); // select.selectByVisibleText("Volvo"); BASED ON VISIBILITY......
        selectMulti.selectByValue("audi");

        // OLD SELECT MENU
        WebElement oldStyleMenu = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(oldStyleMenu);
        select.selectByVisibleText("Purple");

        // select.selectByValue("4"); IT MEANS BY VALUE ATTRIBUTE
        // select.selectByIndex(2); //  by Position
    }

    @Test
    public void mouseHoverTest() {
        driver.get(BASE_URL + "/tool-tips");

        WebElement hoverButton = driver.findElement(By.id("toolTipButton"));

        Actions actions = new Actions(driver);
//        actions.moveToElement(hoverButton).perform(); If the tooltip is not triggered, try moving to the center or a slight offset:
        actions.moveToElement(hoverButton, 5, 5).perform();

        // Wait for the tooltip to appear
        WebElement tooltip = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("tooltip-inner"))
        );

        // Assert the tooltip text
        String expectedTooltip = "You hovered over the Button";
        Assert.assertEquals(tooltip.getText(), expectedTooltip, "Tooltip text does not match");
    }

    @Test
    public void dragAndDropTest() {
        driver.get(BASE_URL + "/droppable");

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }

    @Test
    public void fileUploadTest() {
        driver.get(BASE_URL + "/upload-download");

        WebElement uploadElement = driver.findElement(By.id("uploadFile"));

        uploadElement.sendKeys("C:\\Users\\AdeshDhanrajBode\\Downloads\\image-removebg-preview.png");
    }

    @Test
    public void fileDownloadTest() {
        driver.get(BASE_URL +  "/upload-download");

        WebElement downloadButton = driver.findElement(By.id("downloadButton"));

        downloadButton.click();
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}