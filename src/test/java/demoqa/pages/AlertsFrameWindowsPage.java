package demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsFrameWindowsPage {
    protected WebDriver driver;
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
    @FindBy(id = "timerAlertButton")
    protected WebElement timerAlert;
    @FindBy(id = "confirmButton")
    protected WebElement confirmButton;
    @FindBy(id = "confirmResult")
    protected WebElement confirmResult;
    @FindBy(id = "promtButton")
    protected WebElement promptButton;
    @FindBy(id = "promptResult")
    protected WebElement promptResult;
    @FindBy(xpath = "//li[@id='item-2']//span[text()='Frames']")
    protected WebElement frames;
    @FindBy(xpath = "//div[@id='framesWrapper']//div[text()='Sample Iframe page There are 2 Iframes in this page. Use browser inspecter or firebug to check out the HTML source. " +
            "In total you can switch between the parent frame, which is this window, and the two frames below']")
    protected WebElement parentFrameText;
    @FindBy(xpath = "//iframe[@id='frame1']")
    protected WebElement childFrame1;
    @FindBy(id = "frame2")
    protected WebElement childFrame2;
    @FindBy(xpath = "//li[@id='item-3']//span[text()='Nested Frames']")
    protected WebElement nestedFrames;
    @FindBy(xpath = "//iframe[@src='/sampleiframe']")
    protected WebElement parentNestedFrame;
    @FindBy(xpath = "(//script[@src='chrome-extension://mooikfkahbdckldjjndioackbalphokd/assets/prompt.js'])[4]")
    protected WebElement parentNestedFrameText;
    @FindBy(xpath = "(//script[@src='chrome-extension://mooikfkahbdckldjjndioackbalphokd/assets/prompt.js'])[5]")
    protected WebElement childNestedFrameText;
    @FindBy(xpath = "//iframe[@srcdoc='<p>Child Iframe</p>']")
    protected WebElement childNestedFrame;
}
