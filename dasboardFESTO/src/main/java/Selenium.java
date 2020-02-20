import com.sun.jdi.BooleanValue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Selenium {
    static WebDriver BrowserDriver;



    public static void main(String[] args){

        System.out.println("Selenium");
    }

    static public void setupChrome(){
        //System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        BrowserDriver = new ChromeDriver();
        BrowserDriver.get("http://developdashboard.azurewebsites.net/login");
    }

    static public void setupFireFox(){
            WebDriverManager.firefoxdriver().setup();
            BrowserDriver = new FirefoxDriver();
            BrowserDriver.get("http://developdashboard.azurewebsites.net/login");
    }



    //Login formos uzpildymas
    static public void MonitoringDashboardLogin(String email, String password) {

        //Enter email
        waitForElementById("email");

        WebElement emailInput = BrowserDriver.findElement(By.id("email"));
        emailInput.sendKeys(email);
        String validationMessage = emailInput.getAttribute("validationMessage");

        boolean valid = (Boolean)((JavascriptExecutor)BrowserDriver).executeScript("return arguments[0].validity.valid;", emailInput);
        if(!valid ){
            System.out.println(validationMessage);
        }

        //Enter password
        waitForElementById("password");
        WebElement passwordInput = BrowserDriver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        //Press submit button
        waitForElementByXpath("//button");
        WebElement submitButton = BrowserDriver.findElement(By.xpath("//button"));
        submitButton.click();

    }


    //Atidaro nauja taba narsykleje
    static public void OpenNewTab(int switchNumber, String url){
        ((JavascriptExecutor)BrowserDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(BrowserDriver.getWindowHandles());
        BrowserDriver.switchTo().window(tabs.get(switchNumber));
        BrowserDriver.get(url);
    }

    //Logoutinimas
    static public void NavigateLogout(){
        //Click burger
        waitForElementByClassName("menuIcon");
        WebElement burgerMenu = BrowserDriver.findElement(By.className("menuIcon"));
        burgerMenu.click();
        //Click logout
        waitForElementByXpath("//a[@href='/logout']//button[@class=\"buttonDesign\"]");
        WebElement logoutButton = BrowserDriver.findElement(By.xpath("//a[@href='/logout']//button[@class=\"buttonDesign\"]"));
        logoutButton.click();
    }

    static public void AddNewUser(String email, String name, String surname, String password, String repeatPassword){
        //Click burger
        waitForElementByClassName("menuIcon");
        WebElement burgerMenu = BrowserDriver.findElement(By.className("menuIcon"));
        burgerMenu.click();
        //Click users
        waitForElementByXpath("//a[@href='/users']//button[@class=\"buttonDesign\"]");
        WebElement users = BrowserDriver.findElement(By.xpath("//a[@href='/users']//button[@class=\"buttonDesign\"]"));
        users.click();

        //Click add new user
        waitForElementByXpath("//p[@class='text-right pt-3']//button[@class='link-button']");
        WebElement addUserButton = BrowserDriver.findElement((By.xpath("//p[@class='text-right pt-3']//button[@class='link-button']")));
        addUserButton.click();

        //Enter email
        waitForElementByName("email");
        WebElement writeEmail = BrowserDriver.findElement(By.name("email"));
        writeEmail.sendKeys(email);

        //Enter name
        waitForElementByName("name");
        WebElement writeName = BrowserDriver.findElement(By.name("name"));
        writeName.sendKeys(name);

        //Enter Surname
        waitForElementByName("surname");
        WebElement writeSurname = BrowserDriver.findElement(By.name("surname"));
        writeSurname.sendKeys(surname);

        //Enter Password
        waitForElementByName("password0");
        WebElement writePassword0 = BrowserDriver.findElement(By.name("password0"));
        writePassword0.sendKeys(password);

        //Enter repeatPassword
        waitForElementByName("password");
        WebElement writePassword = BrowserDriver.findElement(By.name("password"));
        writePassword.sendKeys(repeatPassword);

        //Mark checkbox
        //Enter repeatPassword
        waitForElementByName("isactive");
        WebElement isActiveCheckbox = BrowserDriver.findElement(By.name("isactive"));
        isActiveCheckbox.click();

        //Enter Submit form
        waitForElementByXpath("//button[@type='submit']");
        WebElement submitAddForm = BrowserDriver.findElement((By.xpath("//button[@type='submit']")));
        submitAddForm.click();
    }

    static public void DeleteUser(){
        //Click burger
        waitForElementByClassName("menuIcon");
        WebElement burgerMenu = BrowserDriver.findElement(By.className("menuIcon"));
        burgerMenu.click();
        //Click users
        waitForElementByXpath("//a[@href='/users']//button[@class=\"buttonDesign\"]");
        WebElement users = BrowserDriver.findElement(By.xpath("//a[@href='/users']//button[@class=\"buttonDesign\"]"));
        users.click();
        //Click delete
        waitForElementByXpath("");
        WebElement deleteUser = BrowserDriver.findElement(By.xpath(""));
        deleteUser.click();
    }


    //ElementsToBeClicked
    static private void waitForElementByName(String name){
        WebDriverWait waitas = new WebDriverWait(BrowserDriver, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.name(name)));
    }
    static private void waitForElementByClassName(String name){
        WebDriverWait waitas = new WebDriverWait(BrowserDriver, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.className(name)));
    }
    static private void waitForElementById(String name){
        WebDriverWait waitas = new WebDriverWait(BrowserDriver, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.id(name)));
    }
    static private void waitForElementByXpath(String name){
        WebDriverWait waitas = new WebDriverWait(BrowserDriver,2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.xpath(name)));
    }

    static private void waitForEelementByXpathVisibility(String name){
        WebDriverWait wait = new WebDriverWait(BrowserDriver,2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(name)));
    }

    static private void waitForElementPresenceByClass(String name ){
        WebDriverWait wait = new WebDriverWait(BrowserDriver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(name)));
    }

    public static void close(){
        BrowserDriver.close();
    }

    public static void quit(){
        BrowserDriver.quit();
    }
}
