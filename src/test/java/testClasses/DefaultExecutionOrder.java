package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class DefaultExecutionOrder {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass  // Open Chrome only once before all tests
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Wait time for elements
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void signIn() {
        // Step 1: Sign In
        WebElement username = driver.findElement(By.id("user-name"));
        Highlighter.highlightElement(driver, username);
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        Highlighter.highlightElement(driver, password);
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        Highlighter.highlightElement(driver, loginButton);
        loginButton.click();

        System.out.println("1. Sign In successful");

        // Step 2: Wait for inventory page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
    }

    @Test(dependsOnMethods = "signIn")  // Runs after signIn test
    public void addItemToCart() {
        // Step 3: Wait for the "Add to Cart" button to be visible
        WebElement addToCartButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-bike-light"))
        );
        Highlighter.highlightElement(driver, addToCartButton);
        addToCartButton.click();

        System.out.println("2. Item added to cart");
    }
    @Test(dependsOnMethods = "addItemToCart")  // Runs after addItemToCart test
    public void viewCart() {
        // Step 4: Open the shopping cart
        WebElement cartIcon = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_link"))
        );
        Highlighter.highlightElement(driver, cartIcon);
        cartIcon.click();

        System.out.println("3. Opened the cart");

    }
    @Test(dependsOnMethods = "viewCart")
    public void Checkout(){
        WebElement checkButton = driver.findElement(By.id("checkout"));
        Highlighter.highlightElement(driver, checkButton);
        checkButton.click();
    }
    @Test (dependsOnMethods = "Checkout")
    public void Info(){
        WebElement firstName = driver.findElement(By.id("first-name"));
        Highlighter.highlightElement(driver, firstName);
        firstName.sendKeys("Huzaifa");

        WebElement lastName = driver.findElement(By.id("last-name"));
        Highlighter.highlightElement(driver, lastName);
        lastName.sendKeys("Ahmed");

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        Highlighter.highlightElement(driver, postalCode);
        postalCode.sendKeys("46000");

        WebElement Continue = driver.findElement(By.id("continue"));
        Highlighter.highlightElement(driver, Continue);
        Continue.click();
    }

    @AfterClass  // Close Chrome after all tests
    public void tearDown() {
        driver.quit();
    }
}