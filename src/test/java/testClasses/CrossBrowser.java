package testClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowser {
    WebDriver driver;

    @Test
    @Parameters({"URL", "BrowserType"})
    public void verifyTAU(String url, String browserType) {
        if (browserType.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();

        } else if (browserType.equalsIgnoreCase("Internet Explorer")) {
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.get(url);
        System.out.println("\n" + "Open " + browserType);
        System.out.println("   " + driver.getTitle());
        System.out.println("Close " + browserType + "\n");

        driver.quit();
    }
}