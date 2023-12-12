package com.orangehrmlive.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.LoginTestData;

@Listeners(CustomListeners.class)

public class LoginTest extends BaseTest{
    //Declaration
    AddUserPage addUserPage;
    AdminPage adminPage;
    DashboardPage dashboardPage;
    HomePage homePage;
    LoginPage loginPage;
    ViewSystemUserPage viewSystemUserPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt()
    {
        //Initialisation
        addUserPage= new AddUserPage();
        adminPage = new AdminPage();
        dashboardPage = new DashboardPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        viewSystemUserPage = new ViewSystemUserPage();
    }

    @Test(groups={"Sanity" , "Smoke" ,"Regression"})
    public void verifyUserShouldLoginSuccessfully(){
        //Enter username
        homePage.setEnterUserName("Admin");
        //   Enter password
        homePage.setEnterPassword("admin123");
        //   Click on Login Button
        loginPage.setClickOnLogin();
        //   Verify "Dashboard" Message
        String actualMessage = dashboardPage.getVerifyTheTextDashboard();
        String expectedMessage = "Dashboard";
        Assert.assertEquals(actualMessage,expectedMessage,"WelCome Text is displayed");
    }

    @Test(groups = {"Smoke" , "Regression"})
    public void verifyThatTheLogoDisplayedOnLoginPage(){
        //Launch the application
        driver.getCurrentUrl();
        //   Verify Logo is Displayed
        homePage.setHrmLogo();
    }

    @Test(groups = {"Regression" })
    public void verifyUserShouldLogoutSuccessfully() throws InterruptedException{
        //Login To The application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();
        //   Click on User Profile logo
        adminPage.setClickOnUserProfileLogo();
        //   Mouse hover on "Logout" and click
        adminPage.setMouseHoverOnLogout();
        //   Verify the text "Login Panel" is displayed
        Thread.sleep(2000);
        String actualMessage = homePage.setVerifyLoginPanel();
        String expectedMessage = "Login";
        Assert.assertEquals(actualMessage,expectedMessage,"login message is not displayed");
    }

    @Test(dataProvider = "data set" , dataProviderClass = LoginTestData.class)
    public void verifyErrorMessageWithInvalidCredentials(String userName , String password , String errorMessage)
    {
        homePage.setEnterUserName(userName);
        homePage.setEnterPassword(password);
        loginPage.setClickOnLogin();
        String actualMessage = dashboardPage.getVerifyTheTextDashboard();
        String expectedMessage = errorMessage;
        Assert.assertEquals(actualMessage,expectedMessage,"Welcome  Text is not displayed");
    }
}
