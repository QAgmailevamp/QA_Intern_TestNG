package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class DependsOnMethod
{
    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void test1_SetUpChrome()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasir\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time

        System.out.println("1. Set up Chrome.");
    }

    @Test(dependsOnMethods ="test1_SetUpChrome" )
    public void test2_OpenOrangeHRM()
    {
        driver.get("https://opensource-demo.orangehrmlive.com/");

//        Assert.assertEquals(false, true, "Could Not Access OrangeHRM");
        System.out.println("2. Open OrangeHRM");
    }

    @Test(dependsOnMethods ="test2_OpenOrangeHRM" )
    public void test3_SignIn()
    {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        Highlighter.highlightElement(driver, username);
        username.sendKeys("Admin");

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        Highlighter.highlightElement(driver, password);
        password.sendKeys("admin123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        Highlighter.highlightElement(driver, loginButton);
        loginButton.click();

        System.out.println("3. Sign In.");
    }

    @Test(dependsOnMethods ="test3_SignIn" )
    public void test4_SearchUser()
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

        System.out.println("4. Search for user.");


    }

    @Test(dependsOnMethods = {"test2_OpenOrangeHRM", "test3_SignIn" })
    public void test5_SearchEmployee()
    {
        WebElement pimMenu = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@href, 'viewPimModule')]")));
        Highlighter.highlightElement(driver, pimMenu);
        pimMenu.click();


        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")));
        Highlighter.highlightElement(driver, searchButton);
        searchButton.click();

        System.out.println("4. Search for employee.");


    }

    @Test(dependsOnMethods = {"test2_OpenOrangeHRM", "test3_SignIn" })
    public void test6_SearchCandidate ()
    {
        WebElement recruitmentMenu = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@href, 'viewRecruitmentModule')]")));
        Highlighter.highlightElement(driver, recruitmentMenu);
        recruitmentMenu.click();

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")));
        Highlighter.highlightElement(driver, searchButton);
        searchButton.click();

        System.out.println("6. Search For Candidate");
    }

    @Test(dependsOnMethods = {"test2_OpenOrangeHRM", "test3_SignIn" })
    public void test7_SignOut()
    {
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//p[contains(@class, 'oxd-userdropdown-name')]")));
        Highlighter.highlightElement(driver, adminMenu);
        adminMenu.click();

        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@class, 'oxd-userdropdown-link') and contains(@href, '/auth/logout')]")));
        Highlighter.highlightElement(driver, logout);
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
