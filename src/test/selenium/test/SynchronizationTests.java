package com.epam.campus.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SynchronizationTests {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = "https://demoqa.com";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testWithImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(BASE_URL + "/dynamic-properties");

        WebElement enableAfterBtn = driver.findElement(By.id("enableAfter"));

        Assert.assertTrue(
                enableAfterBtn.isDisplayed(),
                "Enable After button is not displayed"
        );
    }

    @Test
    public void testWithExplicitWait() {
        driver.get(BASE_URL + "/dynamic-properties");

        WebElement visibleAfterBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter"))
        );

        Assert.assertTrue(
                visibleAfterBtn.isDisplayed(),
                "Visible After button is not displayed"
        );

        WebElement clickableBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("enableAfter"))
        );

        Assert.assertTrue(
                clickableBtn.isEnabled(),
                "Enable After button is not clickable"
        );

        clickableBtn.click();
    }

    @Test
    public void testWithFluentWait() {
        driver.get(BASE_URL + "/dynamic-properties");

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement colorChangeButton = fluentWait.until(driver ->
                driver.findElement(By.id("colorChange"))
        );

        String initialClass = colorChangeButton.getAttribute("class");

        fluentWait.until(driver ->
                !driver.findElement(By.id("colorChange"))
                        .getAttribute("class")
                        .equals(initialClass)
        );

        String updatedClass = colorChangeButton.getAttribute("class");

        Assert.assertNotEquals(
                updatedClass,
                initialClass,
                "Button color class did not change"
        );
    }

    @Test
    public void testDynamicPageUpdates() {
        driver.get(BASE_URL + "/progress-bar");

        WebElement startStopButton = driver.findElement(By.id("startStopButton"));
        startStopButton.click();

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.className("progress-bar"),
                        "100%"
                )
        );

        WebElement progressBar = driver.findElement(By.className("progress-bar"));

        Assert.assertEquals(
                progressBar.getText(),
                "100%",
                "Progress bar did not reach 100%"
        );

        WebElement resetButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("resetButton"))
        );

        Assert.assertTrue(
                resetButton.isDisplayed(),
                "Reset button is not displayed after progress completion"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}