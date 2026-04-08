package test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


// USE :: WebDriver driver = DriverFactory.createDriver("chrome", true);
public class RemoteDriverFactory {

    private static final String GRID_URL = "http://localhost:4444/wd/hub";

    private RemoteDriverFactory() {
        // prevent instantiation
    }

    public static WebDriver createDriver(String browser, boolean useGrid) {
        if (browser == null || browser.isBlank()) {
            throw new IllegalArgumentException("Browser cannot be null or empty");
        }

        try {
            if (useGrid) {
                return switch (browser.toLowerCase()) {
                    case "chrome" -> new RemoteWebDriver(
                            new URL(GRID_URL),
                            new ChromeOptions()
                    );

                    case "firefox" -> new RemoteWebDriver(
                            new URL(GRID_URL),
                            new FirefoxOptions()
                    );

                    case "edge" -> new RemoteWebDriver(
                            new URL(GRID_URL),
                            new EdgeOptions()
                    );

                    default -> throw new IllegalArgumentException("Browser not supported: " + browser);
                };
            } else {
                return switch (browser.toLowerCase()) {
                    case "chrome" -> new org.openqa.selenium.chrome.ChromeDriver();
                    case "firefox" -> new org.openqa.selenium.firefox.FirefoxDriver();
                    case "edge" -> new org.openqa.selenium.edge.EdgeDriver();
                    default -> throw new IllegalArgumentException("Browser not supported: " + browser);
                };
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Grid URL", e);
        }
    }
}