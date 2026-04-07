package selenium.test;

import com.epam.campus.selenium.Factory.DriverFactory;
import org.openqa.selenium.WebDriver;

//import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

/**
 * Basic cross-browser test demonstrating navigation,
 * title validation, and refresh functionality.
 */

public class BrowserTest {
    WebDriver driver;
    WebDriverWait wait;

    private static  final String BASE_URL = "https://www.google.com";
    private static final String EXPECTED_TITLE = "Google";
    private static final int TIMEOUT_SECONDS = 10;
    private static final int IMPLICIT_WAIT_SECONDS = 5;


    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
//    public void setUp(String browser) {
        driver = DriverFactory.createDriver(browser);

        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));


        wait =  new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        System.out.println("Browser initialized successfully.");
    }

    @Test
    public void testBasicBrowserActions() {

        try{
            // Navigate to the base URL
            driver.get(BASE_URL);

            // Fetch the page title
            String actualTitle = driver.getTitle();

            // Validate the page title
            Assert.assertEquals(actualTitle, EXPECTED_TITLE);

            System.out.println("Title validated successfully: " + actualTitle);

            // Refresh the page - JUST FOR DEMO PURPOSE
            driver.navigate().refresh();
            System.out.println("Page refreshed successfully.");

        }catch (Exception e){
            System.out.println("Test Execution Failed" + e.getMessage());
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true) // runs this even if test fails/ throws exception / dependecy fails
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
