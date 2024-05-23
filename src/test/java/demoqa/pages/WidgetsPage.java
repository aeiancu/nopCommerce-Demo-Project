package demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WidgetsPage {
    protected WebDriver driver;
    public WidgetsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@id='item-0']//span[text()='Accordian']")
    protected WebElement accordion;
    @FindBy(id = "section1Content")
    protected WebElement section1Content;
    @FindBy(id = "section2Heading")
    protected WebElement section2Heading;
    @FindBy(id = "section2Content")
    protected WebElement section2Content;
    @FindBy(id = "section3Heading")
    protected WebElement section3Heading;
    @FindBy(id = "section3Content")
    protected WebElement section3Content;
    @FindBy(xpath = "//li[@id='item-1']//span[text()='Auto Complete']")
    protected WebElement autoComplete;
    @FindBy(id = "autoCompleteMultipleInput")
    protected WebElement multipleColorsField;
    @FindBy(xpath = "//div[text()='Black']")
    protected WebElement blackColor;
    @FindBy(xpath = "//div[text()='White']")
    protected WebElement whiteColor;
    @FindBy(xpath = "//div[text()='Red']")
    protected WebElement redColor;
    @FindAll({@FindBy(xpath = "//div[@class='css-1rhbuit-multiValue auto-complete__multi-value']")})
    protected List<WebElement> selectedOptionsMultipleItemsField;
    @FindBy(xpath = "(//div[@class='css-xb97g8 auto-complete__multi-value__remove'])[1]")
    protected WebElement blackColorRemove;
    @FindBy(xpath = "(//div[@class='css-xb97g8 auto-complete__multi-value__remove'])[2]")
    protected WebElement whiteColorRemove;
    @FindBy(xpath = "(//div[@class='css-xb97g8 auto-complete__multi-value__remove'])[3]")
    protected WebElement redColorRemove;
    @FindBy(id = "autoCompleteSingleInput")
    protected WebElement singleColorsField;
    @FindBy(xpath = "//div[text()='Magenta']")
    protected WebElement magentaColor;
    @FindAll({@FindBy(xpath = "//div[@class='auto-complete__value-container auto-complete__value-container--has-value css-1hwfws3']")})
    protected List<WebElement> selectedOptionsSingleItemField;

}
