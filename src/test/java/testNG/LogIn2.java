package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogIn2
{
    private WebDriver driver;
    private WebDriverWait wait;

    @Test(dataProvider = "login-provider")
    public void logIn(String email, String password, boolean success)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasir\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time

        driver.get("https://www.facebook.com/");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        username.sendKeys(email);

        WebElement passwrd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("pass")));
        passwrd.sendKeys(password);

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();


        System.out.println("Log In Credentials:" + "\n" +
                "Email = " + email + "\n" +
                "Password = " + password + "\n" +
                "Successful LogIn = " + success + "\n" );
    }

    @DataProvider(name = "login-provider")
    public Object [][] logInData()
    {
        Object [][] data = new Object[3][3];

        data[0][0] = "tahreem4085@gmail.com";     data[0][1] = "1234Faceb00k";      data[0][2] = true;
        data[1][0] = "John@Doe.com";     data[1][1] = "DoeDoe34";      data[1][2] = false;
        data[2][0] = "Test@AutomationU.com";     data[2][1] = "TAU1234";      data[2][2] = false;

        return data;
    }
}
