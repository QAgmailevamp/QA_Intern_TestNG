package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeDP
{
    private WebDriver driver;
    private WebDriverWait wait;

    @Test(dataProviderClass = SignInDP.class, dataProvider = "signin-provider")
    public void logIn(String usename, String password, boolean success)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasir\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time

        driver.get("https://opensource-demo.orangehrmlive.com/");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys(usename);

        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        pass.sendKeys(password);

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();


        System.out.println("Log In Credentials:" + "\n" +
                "Username = " + usename + "\n" +
                "Password = " + password + "\n" +
                "Successful LogIn = " + success + "\n" );

        driver.quit();
    }
}
