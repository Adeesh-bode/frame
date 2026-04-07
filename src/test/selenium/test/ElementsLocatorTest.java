package selenium.test;

import com.epam.campus.selenium.Factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ElementsLocatorTests {
    WebDriver driver;
    WebDriverWait wait;


    private static final String BASE_URL = "https://automationexercise.com";
    private static final int TIMEOUT_SECONDS = 20;
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

//    @Test
//    public void testBasicBrowserActions(){
//        try{
//            driver.get(BASE_URL + "/text-box");
//
//        } catch (Exception e) {
//            System.out.println("Exception occurred: " + e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    public void getElementById(){
        driver.get(BASE_URL);
        WebElement footer = driver.findElement(By.id("footer"));
        Assert.assertNotNull(footer);
        Assert.assertTrue(footer.isDisplayed());
    }

    @Test
    public void testLogin() {
        driver.get(BASE_URL + "/login");

        WebElement email= driver.findElement(By.name("email"));
        email.sendKeys("genesis@gmail.com");
        Assert.assertEquals(email.getAttribute("value"), "genesis@gmail.com");

//        WebElement password= driver.findElement(By.name("password"));
//        password.sendKeys("pass");
//        Assert.assertEquals(password.getAttribute("value"), "pass");

        WebElement password= driver.findElement(By.cssSelector("#form > div > div.row > div.col-sm-4.col-sm-offset-1 > div > form > input[type=password]:nth-child(3)"));
        password.sendKeys("pass");
        Assert.assertEquals(password.getAttribute("value"), "pass");

        WebElement loginButton= driver.findElement(By.xpath("//*[@id=\"form\"]/div/div[1]/div[1]/div/form/button"));
        loginButton.click();
//
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "/");
        System.out.println("Login successful.");
    }

    @Test
    public void testDynamicElements() {
        driver.get(BASE_URL);

        // Assume add-to-cart buttons may have dynamic attributes
        WebElement dynamicAdd = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/ul/li/a"));
        WebElement dynamicCss = driver.findElement(By.cssSelector("body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div > div.choose > ul > li > a"));

        Assert.assertTrue(dynamicAdd.isDisplayed());
        Assert.assertTrue(dynamicCss.isDisplayed());
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if(driver != null){
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
