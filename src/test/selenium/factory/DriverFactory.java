package com.epam.campus.selenium.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.DriverManager;

public class DriverFactory {

    private DriverFactory() {
        // prevent instantiation
    }

    public static WebDriver createDriver(String browser) {
        if(browser == null || browser.isBlank()){ // blanks check 0 length or only whitespaces
            throw new IllegalArgumentException("Browser cannot be null or empty");
        }

        return switch (browser) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> throw new IllegalArgumentException("Browser not supported: " + browser);
        };
    }
}
