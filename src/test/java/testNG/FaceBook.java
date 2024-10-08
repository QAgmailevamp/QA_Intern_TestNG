package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class FaceBook
{
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeClass
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasir\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time


        System.out.println("Opened Chrome and application.");
    }

    @BeforeMethod
    public void loginTest()
    {
        driver.get("https://www.facebook.com/");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        username.sendKeys("tahreem4085@gmail.com");

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("pass")));
        password.sendKeys("1234Faceb00k");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();

        System.out.println("Logged In.");
    }

    @Test
    public void searchUser()
    {

        WebElement search = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//input[@placeholder='Search Facebook']")));
        search.click();
        search.sendKeys("tahreem");
        search.sendKeys(Keys.ENTER);

        System.out.println("Searched user.");


    }

    @AfterMethod
    public void profile()
    {
        try {
                Thread.sleep(4000); // Delay for 4 seconds
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        WebElement home = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@href, '/')]")));
        home.click();

        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@class, 'x1lliihq x6ikm8r x10wlt62 x1n2onr6')]")));
        profile.click();

        System.out.println("opened home");


    }

    @AfterMethod
    public void userSignOut()
    {
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(@class, 'oxd-userdropdown-name')]")));
        adminMenu.click();
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'oxd-userdropdown-link') and contains(@href, '/auth/logout')]")));
        logout.click();

        System.out.println("Logged out.");
    }


    @AfterClass
    public void tearDown()
    {
        if (driver != null)
        {
            try {
                Thread.sleep(4000); // Delay for 4 seconds
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
            driver.quit();
        }

        System.out.println("Closed chrome.");
    }

}
