package nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

public class FormSubmissionPage {

    protected WebDriver driver;
    protected WebElement fullName;
    protected WebElement email;
    protected WebElement enquiry;
    protected WebElement submit;
    protected WebElement nameErrorMessage;
    protected WebElement emptyEmailErrorMessage;
    protected WebElement enquiryErrorMessage;
    protected WebElement wrongEmailErrorMessage;



    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/contactus");

        //Locate the elements
        fullName = driver.findElement(By.id("FullName"));
        email = driver.findElement(By.id("Email"));
        enquiry = driver.findElement(By.id("Enquiry"));
        submit = driver.findElement(By.name("send-email"));

    }


}
