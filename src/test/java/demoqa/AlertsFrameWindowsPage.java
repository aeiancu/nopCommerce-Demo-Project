package demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsFrameWindowsPage {
    WebDriver driver;
    public AlertsFrameWindowsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//li[@id='item-0']//span[text()='Browser Windows']")
    protected WebElement browserWindows;
    @FindBy(id = "tabButton")
    protected WebElement newTabBtn;
    @FindBy(id = "windowButton")
    protected WebElement newWindowBtn;
    @FindBy(id = "messageWindowButton")
    protected WebElement newWindowMessageBtn;
    @FindBy(xpath = "//li[@id='item-1']//span[text()='Alerts']")
    protected WebElement alerts;
    @FindBy(id = "alertButton")
    protected WebElement standardAlert;

}
