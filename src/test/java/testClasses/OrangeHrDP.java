package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHrDP
{
    WebDriver driver;
    private WebDriverWait wait;

    @Test (dataProviderClass = SignInDP.class, dataProvider = "signin-provider")
    public void signIn (String usename, String password, boolean success)
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://opensource-demo.orangehrmlive.com");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        Highlighter.highlightElement(driver,  username);
        username.sendKeys(usename);

        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        Highlighter.highlightElement(driver,  pass);
        pass.sendKeys(password);

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        Highlighter.highlightElement(driver, loginButton);
        loginButton.click();

        System.out.println("Sign In Credentials: " + "\n" +
                "  Username = " + usename + "\n" +
                "  Password = " + password + "\n" +
                "  Successful Sign In = " + success + "\n" );


        driver.quit();
    }
}