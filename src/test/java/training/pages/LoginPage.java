package training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import java.util.UUID;

public class LoginPage {

    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='button']")
    protected WebElement acceptCookies;
    @FindBy(css = "div[data-popup-handler='auth']")
    protected WebElement loginButton;
    @FindBy(css = "a[href='/register/']")
    protected WebElement registerButton;
    @FindBy(xpath = "//div[@data-popup-handler='forget-password']")
    protected WebElement forgotPasswordButton;
    @FindBy(id = "name")
    protected WebElement lastName;
    @FindBy(id = "surname")
    protected WebElement firstName;
    @FindBy(id = "birthday")
    protected WebElement dateOfBirth;
    @FindBy(xpath = "(//div[@class='custom-select'])[1]")
    protected WebElement dateOfBirthYear;
    @FindBy(xpath = "//div[@data-value='1992']")
    protected WebElement yearOption;
    @FindBy(xpath = "(//div[@class='custom-select'])[2]")
    protected WebElement dateOfBirthMonth;
    @FindBy(xpath = "//div[@data-value='4']")
    protected WebElement monthOption;
    @FindBy(css = "span[class='day']")
    protected WebElement dayOption;
    @FindBy(id = "phone")
    protected WebElement phoneNumber;
    @FindBy(id = "email")
    protected WebElement emailAddressField;
    protected String registeredEmail;
    protected WebElement getNotificationsCheckbox;
    @FindBy(id = "password")
    protected WebElement passwordField;
    @FindBy(id = "repeat-password")
    protected WebElement confirmPassword;
    @FindBy(xpath = "//button[@class='button']")
    protected WebElement submitButton;
    @FindBy(xpath = "//div[@class='registration-done']")
    protected WebElement registrationDone;
    @FindBy(xpath = "//a[@class='header-office authorized']")
    protected WebElement accountDetails;
    @FindBy(xpath = "//a[@href='/user/logout/']")
    protected WebElement logOutButton;
    @FindBy(id = "login")
    protected WebElement loginEmail;
    @FindBy(id = "pw")
    protected WebElement loginPassword;
    @FindBy(xpath = "(//button[@type='submit'])[3]")
    protected WebElement authenticationButton;


    public static String generateRandomEmailAddress() {
        String randomUUID = UUID.randomUUID().toString();
        randomUUID = randomUUID.replace("-", "");
        String randomAddress = randomUUID + "@example.com";
        return randomAddress;
    }


   @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
       Object[][] data = {
               {"user1@example.com", "password1"},
               {"user2@example.com", "password2"}
       };
       return data;
   }



}
