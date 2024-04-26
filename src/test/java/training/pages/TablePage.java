package training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TablePage {

    protected WebDriver driver;

    public TablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindAll({@FindBy(xpath = "//td[@class='column-8']")})
    protected List <WebElement> ageColumn;

    @FindBy(xpath = "//th[@tabindex='0']")
    protected WebElement sortByFirstName;

    @FindAll({@FindBy(xpath = "//td[@class='column-1']")})
    protected List <WebElement> firstNameColumn;

    @FindBy(xpath = "//select[@name='tablepress-2_length']")
    protected WebElement selectEntries;
}
