package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BM_AM_GoogleSearch {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path to the ChromeDriver executable, if necessary
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to Google Search
        driver.get("https://www.google.com/");
        System.out.println("1. Open Chrome & Google Search");
    }

    @Test
    public void googleSearch() {
        // Locate the search box on Google homepage
        WebElement searchBox = driver.findElement(By.name("q"));
        highlightElement(driver, searchBox); // Optional: highlight the search box
        searchBox.sendKeys("Selenium WebDriver");

        // Submit the search form
        searchBox.submit();
        System.out.println("2. Perform Google Search");

        // Optional: wait for results to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify if the results page has loaded by checking the title
        if (driver.getTitle().contains("Selenium WebDriver")) {
            System.out.println("3. Search results are displayed");
        } else {
            System.out.println("3. Search results not found");
        }
    }

    @Test
    public void verifyResult() {
        // Perform the search first (reuse the googleSearch test case)
        googleSearch();

        // Click on the first search result link
        WebElement firstResult = driver.findElement(By.xpath("//h3"));
        highlightElement(driver, firstResult); // Optional: highlight the first result
        firstResult.click();
        System.out.println("4. Clicked on the first search result");

        // Optional: wait for the new page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the first result page has loaded by checking the title
        System.out.println("5. Current page title: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("6. Close Chrome & Application");
        driver.quit();
    }

    // Utility method to highlight elements (similar to Highlighter class in your original code)
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', '');", element); // Reset style
    }
}