package testNG;

import org.openqa.selenium.*;
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

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void searchUser()
    {

        WebElement search = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//input[@placeholder='Search Facebook']")));
        search.click();
        search.sendKeys("tahreem");
        search.sendKeys(Keys.ENTER);

        System.out.println("Searched user.");


    }

    @Test(priority = 3)
    public void profile()
    {
        try
      {
        WebElement home = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/')]")));
        home.click();

        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'profile.php?id=') and contains(@class, 'x1lliihq')]")));


        System.out.println("Profile element found: " + profile.isDisplayed());
        profile.click();

        System.out.println("Navigated to profile.");
      }
      catch (Exception e)
      {
        System.err.println("Failed to navigate to profile: " + e.getMessage());
      }
    }


    @Test(priority = 4)
    public void userSignOut()
    {
        try
        {
        WebElement user = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//div[@aria-label='Your profile']")));
        user.click();

            try {
                Thread.sleep(4000); // Delay for 4 seconds
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }

            WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Log out')]")));
            System.out.println("Logout button found: " + logout.isDisplayed() + ", Enabled: " + logout.isEnabled());

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);


            System.out.println("Logged out.");

        }
        catch (Exception e)
        {
    System.err.println("Sign out failed: " + e.getMessage());
        }
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
