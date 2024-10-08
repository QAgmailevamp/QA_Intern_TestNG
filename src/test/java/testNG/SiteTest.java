package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.*;

public class SiteTest
{
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasir\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time

        System.out.println("Opened Chrome and application.");
    }

    @BeforeMethod
    public void loginTest()
    {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("Admin");

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.sendKeys("admin123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();

        System.out.println("Logged In.");
    }

    @Test()
    public void searchUserInAdminPanel()
    {
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@class, 'oxd-main-menu-item') and contains(@href, 'viewAdminModule')]")));
        adminMenu.click();

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.oxd-form-row input.oxd-input.oxd-input--active")));
        usernameInput.sendKeys("Admin");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")));
        searchButton.click();

        System.out.println("Searched user.");


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





