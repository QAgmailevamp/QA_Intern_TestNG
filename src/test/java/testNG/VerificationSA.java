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
import org.testng.asserts.SoftAssert;

public class VerificationSA
{
    SoftAssert softassert = new SoftAssert();
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasir\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time

        System.out.println("1. Open Chrome and application.");
    }

    @Test
    public void loginTest()
    {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        Highlighter.highlightElement(driver,  username);
        username.sendKeys("Admin");

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        Highlighter.highlightElement(driver,  password);
        password.sendKeys("admin123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        Highlighter.highlightElement(driver, loginButton);
        loginButton.click();

        System.out.println("2. Add username and password.");
    }


    @Test
    public void testHomePageVerification ()
    {
        softassert.assertEquals(true, false, "The Welcome Link Is Not Correct On The Home Page");
        System.out.println("3. Verify Welcome Link");

        softassert.assertFalse(false, "The Admin Tab Is Not Displayed On The Home Page");
        System.out.println("4. Verify Admin Tab");

        softassert.assertTrue(false, "The Dashboard Is Not Correct On The Home Page");
        System.out.println("5. Verify Dashboard");

        softassert.assertAll();
    }




    @Test
    public void searchUserInAdminPanel()
    {
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@class, 'oxd-main-menu-item') and contains(@href, 'viewAdminModule')]")));
        Highlighter.highlightElement(driver, adminMenu);
        adminMenu.click();

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.oxd-form-row input.oxd-input.oxd-input--active")));
        Highlighter.highlightElement(driver, usernameInput);
        usernameInput.sendKeys("Admin");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")));
        Highlighter.highlightElement(driver, searchButton);
        searchButton.click();

        System.out.println("6. Open Admin panel and search a user.");


    }

    @Test
    public void userSignOut()
    {
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(@class, 'oxd-userdropdown-name')]")));
        Highlighter.highlightElement(driver, adminMenu);
        adminMenu.click();

        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'oxd-userdropdown-link') and contains(@href, '/auth/logout')]")));
        Highlighter.highlightElement(driver, logout);
        logout.click();

        System.out.println("7. Log out.");
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

        System.out.println("8. Close chrome.");
    }

}





