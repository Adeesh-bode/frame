package test.utils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

public class BrowserStackFactory {

    private static final String USERNAME = "adeshbode_jFAJTXYZZWRONGUSERNAME";
    private static final String ACCESS_KEY = "xyzpVjpk5jVyaTq3xdRBVAvWRONGKEY";
//    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
//    private static final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");

    public static WebDriver getDriver(String browser) throws Exception {

        MutableCapabilities options;

        switch (browser.toLowerCase()) {
            case "edge":
                options = new EdgeOptions();
                options.setCapability("browserName", "Edge");
                break;

            case "firefox":
                options = new FirefoxOptions();
                options.setCapability("browserName", "Firefox");
                break;

            default:
                options = new ChromeOptions();
                options.setCapability("browserName", "Chrome");
                break;
        }

        options.setCapability("browserVersion", "latest");

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "11");
        bstackOptions.put("sessionName", browser + " BrowserStack Test");

        options.setCapability("bstack:options", bstackOptions);

        return new RemoteWebDriver(
                new URL("https://" + USERNAME + ":" + ACCESS_KEY + "@hub.browserstack.com/wd/hub"),
                options
        );
    }
}