package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowser
{
    WebDriver driver;

    @Test
    @Parameters({"URL", "BrowserType"})
    public void verifyTAU(String url, String browserType)
    {
        if (browserType.equalsIgnoreCase("Edge"))
        {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\yasir\\Downloads\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();

        }
        else if (browserType.equalsIgnoreCase("Chrome"));
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasir\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();

        }

        driver.manage().window().maximize();
        driver.get(url);

        System.out.println("\n" + "Open " + browserType);
        System.out.println("   " +  driver.getTitle());
        System.out.println("Close " + browserType + "\n");

        driver.quit();
    }
}
