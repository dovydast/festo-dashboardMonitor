import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumUsersTest {


/*
    @BeforeTest
    public void testSetupChrome(){
        Selenium.setupChrome();
    }
*/

    @BeforeTest
    public void testSetupFirefox(){
        Selenium.setupFireFox();
    }



// Testuojama user add
@Test(priority = 1)
public void testUserAddPositiveValues(){
    Selenium.MonitoringDashboardLogin("login@mail.com","Asd123@");
    System.out.println("logged in");
    Selenium.AddNewUser("test@aktyvus.lt","Jonas","Jonaitis","Asd123@","Asd123@");
    System.out.println("user created");
}


@Test(priority = 2)
public void testEditRecentlyCreatedUser(){
        Selenium.OpenNewTab(1,"http://developdashboard3.azurewebsites.net/users");
        Selenium.EditRecentlyCreatedUser("NameTest","SurnameTest","Testing123@","Testing123@");
        System.out.println("user edited");
}
//Testuojam user delete

@Test(priority = 3)
public void testUserDelete(){
    Selenium.OpenNewTab(2,"http://developdashboard3.azurewebsites.net");
    Selenium.DeleteUser();
    System.out.println("user deleted");
    Selenium.NavigateLogout();
    System.out.println("logged out");

}


@AfterTest
    public void closeTest(){
        Selenium.quit();
}
 

}
