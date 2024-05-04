package demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FormsPage {
    protected WebDriver driver;
    public FormsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//li[@id='item-0']//span[text()='Practice Form']")
    protected WebElement practiceForm;
    @FindBy(id = "firstName")
    protected WebElement firstName;
    @FindBy(id = "lastName")
    protected WebElement lastName;
    @FindBy(id = "userEmail")
    protected WebElement userEmail;
    @FindBy(xpath = "(//div[@class='custom-control custom-radio custom-control-inline'])[1]")
    protected WebElement maleGender;
    @FindBy(xpath = "(//div[@class='custom-control custom-radio custom-control-inline'])[2]")
    protected WebElement femaleGender;
    @FindBy(xpath = "(//div[@class='custom-control custom-radio custom-control-inline'])[3]")
    protected WebElement otherGender;
    @FindBy(id = "userNumber")
    protected WebElement userNumber;
    @FindBy(id = "dateOfBirthInput")
    protected WebElement dateOfBirthInput;
    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    protected WebElement dateOfBirthMonth;
    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    protected WebElement dateOfBirthYear;
    @FindAll({@FindBy(css = ".react-datepicker__week .react-datepicker__day")})
    protected List<WebElement> weekDays;
    @FindBy(xpath = "//div[@class='react-datepicker__day react-datepicker__day--018']")
    protected WebElement dateOfBirthDay;
    @FindBy(id = "subjectsInput")
    protected WebElement subjects;
    @FindBy(xpath = "(//label[@class='custom-control-label'])[4]")
    protected WebElement sportsCheckBox;
    @FindBy(xpath = "(//label[@class='custom-control-label'])[5]")
    protected WebElement readingCheckBox;
    @FindBy(xpath = "(//label[@class='custom-control-label'])[6]")
    protected WebElement musicCheckBox;
    @FindBy(id = "uploadPicture")
    protected WebElement uploadPicture;
    @FindBy(id = "currentAddress")
    protected WebElement currentAddress;
    @FindBy(id = "state")
    protected WebElement stateDropdown;
    @FindBy(xpath = "//div[@id='state']//div[text()='Uttar Pradesh']")
    protected WebElement uttarPradeshState;
    @FindBy(id = "city")
    protected WebElement cityDropdown;
    @FindBy(xpath = "//div[@id='city']//div[text()='Agra']")
    protected WebElement agraCity;
    @FindBy(id = "submit")
    protected WebElement submitButton;
    @FindBy(id = "example-modal-sizes-title-lg")
   protected WebElement successfulSubmit;




}
