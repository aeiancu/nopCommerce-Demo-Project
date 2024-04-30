package demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ElementsPage {
    protected WebDriver driver;
    public ElementsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//iframe[@id='google_ads_iframe_/21849154601,22343295815/Ad.Plus-Anchor_0']")
    protected WebElement adClose;

    @FindBy(xpath = "(//li[@id='item-0'])[1]")
    protected WebElement textBox;
    @FindBy(id = "userName")
    protected WebElement userName;
    @FindBy(id = "userEmail")
    protected WebElement userEmail;
    @FindBy(id = "currentAddress")
    protected WebElement currentAddress;
    @FindBy(id = "permanentAddress")
    protected WebElement permanentAddress;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    protected WebElement submit;
    @FindBy(xpath = "(//li[@id='item-1'])[1]")
    protected WebElement checkBox;
    @FindBy(xpath = "(//button[@class='rct-collapse rct-collapse-btn'])[1]")
    protected WebElement homeCollapseButton;
    @FindBy(xpath = "(//button[@class='rct-collapse rct-collapse-btn'])[2]")
    protected WebElement desktopCollapseButton;
    @FindBy(xpath = "(//button[@class='rct-collapse rct-collapse-btn'])[3]")
    protected WebElement documentsCollapseButton;
    @FindBy(xpath = "(//button[@class='rct-collapse rct-collapse-btn'])[4]")
    protected WebElement workspaceCollapseButton;
    @FindBy(xpath = "(//button[@class='rct-collapse rct-collapse-btn'])[5]")
    protected WebElement officeCollapseButton;
    @FindBy(xpath = "(//button[@class='rct-collapse rct-collapse-btn'])[6]")
    protected WebElement downloadsCollapseButton;
    @FindBy(xpath = "(//span[@class='rct-checkbox'])[3]")
    protected WebElement notesCheckbox;
    @FindBy(xpath = "(//span[@class='rct-checkbox'])[4]")
    protected WebElement commandsCheckbox;
    @FindBy(xpath = "(//span[@class='rct-checkbox'])[6]")
    protected WebElement workspaceCheckbox;
    @FindBy(xpath = "(//span[@class='rct-checkbox'])[7]")
    protected WebElement officeCheckbox;
    @FindBy(xpath = "(//span[@class='rct-checkbox'])[9]")
    protected WebElement wordFileCheckbox;
    @FindBy(xpath = "(//span[@class='rct-checkbox'])[10]")
    protected WebElement excelFileCheckbox;
    @FindBy(xpath = "(//li[@id='item-2'])[1]")
    protected WebElement radioButton;
    @FindBy(xpath = "//input[@id='yesRadio']")
    protected WebElement yesRadio;
    @FindBy(id = "impressiveRadio")
    protected WebElement impressiveRadio;
    @FindBy(id = "noRadio")
    protected WebElement noRadio;
    @FindBy(xpath = "(//li[@id='item-3'])[1]")
    protected WebElement webTables;
    @FindAll({@FindBy(xpath = "//div[@style='flex: 40 0 auto; width: 40px; max-width: 40px;']")})
    protected List<WebElement> ageColumn;
    @FindAll({@FindBy(xpath = "//div[@style='flex: 80 0 auto; width: 80px; max-width: 80px;']")})
    protected List<WebElement> salaryColumn;
    @FindAll({@FindBy(xpath = "//div[contains(text(), '@example.com')]")})
    protected List<WebElement> emailColumn;
    @FindAll({@FindBy(xpath = "//div[contains(@class, 'rt-tr-group')]/descendant::div[@class='rt-td' and (text()='Insurance' or text()='Compliance' or text()='Legal')]")})
    protected List<WebElement> departmentColumn;
    @FindBy(xpath = "(//li[@id='item-4'])[1]")
    protected WebElement buttons;
    @FindBy(xpath = "//button[@id='doubleClickBtn']")
    protected WebElement doubleClickBtn;
    @FindBy(xpath = "//button[@id='rightClickBtn']")
    protected WebElement rightClickBtn;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    protected WebElement dynamicClickBtn;
    @FindBy(xpath = "//p[@id='doubleClickMessage']")
    protected WebElement doubleClickMessage;
    @FindBy(xpath = "//p[@id='rightClickMessage']")
    protected WebElement rightClickMessage;
    @FindBy(xpath = "//p[@id='dynamicClickMessage']")
    protected WebElement dynamicClickMessage;
    @FindBy(xpath = "(//li[@id='item-5'])[1]")
    protected WebElement links;
    @FindBy(xpath = "//a[@id='simpleLink']")
    protected WebElement homeLink;
    @FindBy(xpath = "(//div[@class='card-body'])[1]")
    protected WebElement elements;
    @FindBy(xpath = "(//li[@id='item-6'])[1]")
    protected WebElement brokenLinksImages;
    @FindBy(xpath = "//img[@src='/images/Toolsqa.jpg']")
    protected WebElement validImage;
    @FindBy(xpath = "//img[@src='/images/Toolsqa_1.jpg']")
    protected WebElement brokenImage;
    @FindBy(xpath = "//a[@href='http://demoqa.com']")
    protected WebElement validLink;
    @FindBy(xpath = "//a[@href='http://the-internet.herokuapp.com/status_codes/500']")
    protected WebElement brokenLink;
    @FindBy(xpath = "(//li[@id='item-7'])[1]")
    protected WebElement uploadAndDownload;
    @FindBy(id = "downloadButton")
    protected WebElement downloadButton;
    @FindBy(id = "uploadFile")
    protected WebElement uploadFile;
    @FindBy(id = "uploadedFilePath")
    protected WebElement uploadedFilePath;
    @FindBy(xpath = "(//li[@id='item-8'])[1]")
    protected WebElement dynamicProperties;
    @FindBy(id = "enableAfter")
    protected WebElement enabledAfter5SecondsBtn;
    @FindBy(id = "colorChange")
    protected WebElement colorChangeBtn;
    @FindBy(id = "visibleAfter")
    protected WebElement visibleAfter5SecondsBtn;




    //Extract the age list from Elements / Web Table page
    public List<Integer> getTheAgeList(){
        List<Integer> ageList = new ArrayList<>();
        for (int i = 1; i < Math.min(ageColumn.size(), 4); i++) { // Limit loop to first 3 rows
            WebElement age = ageColumn.get(i);
            String ageText = age.getText().trim();
            int ageInt = Integer.parseInt(ageText);
            ageList.add(ageInt);
        }
        return ageList;
    }

    //Extract the email list from Elements / Web Table page
    public List<String> getTheEmailList(){
        List<String> emailList = new ArrayList<>();
        for (WebElement email : emailColumn) {
            String emailText = email.getText();
            emailList.add(emailText);
        }
        return emailList;
    }

    //Extract the salary list from Elements / Web Table page
    public List<Integer> getTheSalaryList() {
        List<Integer> salaryList = new ArrayList<>();
        Pattern digitPattern = Pattern.compile("\\d");

            for (WebElement salary : salaryColumn) {
                String salaryText = salary.getText().trim();
                if (digitPattern.matcher(salaryText).find()) {
                    int salaryInt = Integer.parseInt(salaryText);
                    salaryList.add(salaryInt);
                }
            }
            return salaryList;
    }

    //Extract the department list from Elements / Web Table page
    public List<String> getTheDepartmentList() {
        List<String> departmentList = new ArrayList<>();
        for (WebElement department : departmentColumn) {
            String departmentText = department.getText();
            departmentList.add(departmentText);
        }
        return departmentList;
    }



}
