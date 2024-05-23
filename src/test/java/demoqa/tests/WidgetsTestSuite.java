package demoqa.tests;

import demoqa.pages.WidgetsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WidgetsTestSuite extends WidgetsPage {

    public WidgetsTestSuite() {
        super(null);
    }
    String url = "https://demoqa.com/widgets";
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        int xOffset = 1900;
        int yOffset = 0;
        Point position = new Point(xOffset, yOffset);
        options.addArguments("window-position=" + position.getX() + "," + position.getY());
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        driver.get(url);
    }

    @Test(groups = "Accordion")
    public void testAccordionBehavior() {
        accordion.click();
        Assert.assertTrue(section1Content.isDisplayed(), "Section 1 content should be visible.");
        section2Heading.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("section2Content")));
        Assert.assertTrue(section2Content.isDisplayed(), "Section 2 content should be visible.");
        section2Heading.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        section3Heading.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("section3Content")));
        Assert.assertTrue(section3Content.isDisplayed(), "Section 3 content should be visible.");
    }

    @Test(groups = "Auto Complete")
    public void checkMultipleItemsField() {
        autoComplete.click();
        multipleColorsField.sendKeys("B");
        blackColor.click();
        multipleColorsField.sendKeys("W");
        whiteColor.click();
        multipleColorsField.sendKeys("R");
        redColor.click();
        Assert.assertEquals(selectedOptionsMultipleItemsField.size(), 3, "Three options should be selected.");
    }
    @Test(groups = "Auto Complete")
    public void testDeleteSelectedItemsFromMultipleItemsField() {
        autoComplete.click();
        multipleColorsField.sendKeys("B");
        blackColor.click();
        multipleColorsField.sendKeys("W");
        whiteColor.click();
        multipleColorsField.sendKeys("R");
        redColor.click();
        redColorRemove.click();
        whiteColorRemove.click();
        blackColorRemove.click();
        Assert.assertEquals(selectedOptionsMultipleItemsField.size(), 0, "No option should be selected.");
    }

    @Test(groups = "Auto Complete")
    public void checkSingleItemField() {
        autoComplete.click();
        singleColorsField.sendKeys("M");
        magentaColor.click();
        singleColorsField.sendKeys("R");
        redColor.click();
        Assert.assertEquals(selectedOptionsSingleItemField.size(), 1, "Only one option should be selected.");
    }

    @Test(groups = "Date Picker")
    public void testSelectDate() {
        
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
