package nopcommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormSubmissionPage {
    public FormSubmissionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver driver;
    @FindBy(xpath = "//input[@placeholder='Enter your name.']")
    protected WebElement fullName;
    @FindBy(id = "Email")
    protected WebElement email;
    @FindBy(id = "Enquiry")
    protected WebElement enquiry;
    @FindBy(name = "send-email")
    protected WebElement submit;
    @FindBy(xpath = "//div[@class='result']")
    protected WebElement successfulSubmit;
    @FindBy(id = "FullName-error")
    protected WebElement nameErrorMessage;
    @FindBy(id = "Email-error")
    protected WebElement emptyEmailErrorMessage;
    @FindBy(id = "Enquiry-error")
    protected WebElement enquiryErrorMessage;
    @FindBy(id = "Email-error")
    protected WebElement wrongEmailErrorMessage;

}
