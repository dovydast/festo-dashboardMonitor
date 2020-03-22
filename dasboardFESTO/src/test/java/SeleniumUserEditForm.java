import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumUserEditForm {
/*
    @BeforeTest
    public void testSetupChrome(){
        Selenium.setupChrome();
         Selenium.MonitoringDashboardLogin("login@mail.com","asd123");
    }
*/

    @BeforeTest
    public void testSetupFirefox(){
        Selenium.setupFireFox();
        Selenium.MonitoringDashboardLogin("login@mail.com","Asd123@");
    }


    @Test(priority = 1)
    public void testEditUserNameSurnameLithuanianLetters() {
        String errorMsg = "Only latin letters and numbers";
        Selenium.OpenNewTab(1, "http://developdashboard3.azurewebsites.net/users");
        Selenium.EditExistingUser("Ričardas", "Mačiulis", "Asd123@", "Asd123@");
        Assert.assertEquals(Selenium.LatinLetters(), "Only latin letters and numbers");
        System.out.println(Selenium.LatinLetters());

     

    }

    @Test(priority = 2)
    public void testEditUserNameSurnameFields(){
        Selenium.OpenNewTab(2,"http://developdashboard3.azurewebsites.net/users");
        Selenium.EditExistingUser("SeleniumNameTesting","SeleniumSurnameTesting","Asd123@","Asd123@");
    }


/*
@AfterTest
    public void closeTest(){
        Selenium.quit();
}
 */
}
