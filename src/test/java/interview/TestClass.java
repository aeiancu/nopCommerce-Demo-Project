package interview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//The function should take 2 parameters: getFirstLastIndexCharacter(String s, Character c);
//Given an array and an expected sum, you are required to find the index of the two numbers
// in the array whose sum adds up to the number 7.

public class TestClass {

    @FindBy(xpath = "//button[@class='pum-close popmake-close']")
    private WebElement popupButton;

    @FindBy(xpath = "//a[@href='https://website.multiformis.com/sort-and-tables/']")
    private WebElement sortAndTablesLink;


    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://website.multiformis.com/");
    }

    @Test
    public void popupTestCase() {
        popupButton.click();
        sortAndTablesLink.click();

        List<WebElement> ageColumn = driver.findElements(By.xpath("//td[@class='column-8']"));
        List<Integer> ageItems = new ArrayList<>();
        for (WebElement age : ageColumn) {
            String ageText = age.getText();
            //  System.out.println(age.getText());
            int ageInt = Integer.parseInt(ageText);
            ageItems.add(ageInt);
            //  System.out.println(ageInt);
        }

        int maxAge = Collections.max(ageItems);
        System.out.println(maxAge);

    }



    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
