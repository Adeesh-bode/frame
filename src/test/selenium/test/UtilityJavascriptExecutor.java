package com.example.javascript;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptExecutorTest {

    /**
     * Clicks a hidden element using JavaScriptExecutor.
     * This is useful when the element is hidden or not directly clickable
     * through the normal Selenium click() method.
     *
     * @param driver Selenium WebDriver instance
     * @param element WebElement to be clicked
     */
    public void clickHiddenElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls to the target element and then clicks it using JavaScriptExecutor.
     * This is useful when the element is outside the visible viewport
     * or blocked by another element.
     *
     * @param driver Selenium WebDriver instance
     * @param element WebElement to scroll to and click
     */
    public void scrollToElementAndClick(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll the element into view
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        // Click the element after scrolling
        js.executeScript("arguments[0].click();", element);
    }
}