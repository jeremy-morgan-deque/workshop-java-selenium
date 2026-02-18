package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class RecipeDashboardTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://workshop2.dequelabs.com/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPageTitle() {
        SoftAssert softAssert = new SoftAssert();
        
        String actualTitle = driver.getTitle();
        softAssert.assertEquals(actualTitle, "Recipe Dashboard", "Page title should be 'Recipe Dashboard'");
        
        softAssert.assertAll();
    }

    @Test
    public void testEditRecipeModal() {
        SoftAssert softAssert = new SoftAssert();
        
        // 1. Wait for edit button to exist
        String editButtonSelector = "#main-content > div.Recipes > div:nth-child(1) > div.Recipes__card-head > div.Recipes__card-edit";
        WebElement editButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(editButtonSelector)));
        
        // 2. Click on the edit button
        editButton.click();
        
        // 3. Wait for modal dialog to exist
        String modalSelector = "#main-content > div.Recipes > div.Dialog.Modal.Dialog--show";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(modalSelector)));
        
        // 4. Check that the modal h2 text is "Edit Chocolate Cake"
        String modalHeaderSelector = "#main-content > div.Recipes > div.Dialog.Modal.Dialog--show > div > div > h2";
        WebElement modalHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(modalHeaderSelector)));
        String actualHeaderText = modalHeader.getText();
        softAssert.assertEquals(actualHeaderText, "Edit Chocolate Cake", "Modal header should be 'Edit Chocolate Cake'");
        
        softAssert.assertAll();
    }
}
