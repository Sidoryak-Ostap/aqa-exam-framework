package ui;

import bo.LoginBO;
import driver.DriverPool;
import listeners.AllureListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({
        AllureListener.class
})
public class LoginTest {

    String admin = "admin";
    String password = "admin";
    String wrongPassword = "admin12";
    String expectedUrl = "http://localhost:3000/admin/view-posts";
    String expectedErrorMessage = "Хибний логін або пароль";



    @Test
    void loginSuccessTest() {
        // 1 Open website
        new LoginBO().goToWebsite()
        // 2 Navigate to login page
                .openLoginPage()
        // 3 Focus login input
                .focusLoginInput()
        // 4 Input login
                .fillLoginInput(admin)
        // 5 Focus password input
                .focusPasswordInput()
        // 6 Input password
                .fillPasswordInput(password)
        // 7 Click login button
                .clickLoginBtn()
        // 8 Verify user logged in
                .verifyLogin(expectedUrl);
    }

    @Test()
    void loginFailureTest(){
        // 1 Open website
        new LoginBO().goToWebsite()
                // 2 Navigate to login page
                .openLoginPage()
                // 3 Focus login input
                .focusLoginInput()
                // 4 Input login
                .fillLoginInput(admin)
                // 5 Focus password input
                .focusPasswordInput()
                // 6 Input password
                .fillPasswordInput(wrongPassword)
                // 7 Click login button
                .clickLoginBtn()
                // 8 Verify user failed logged in
                .verifyFailedLogin(expectedErrorMessage);
    }

    @AfterMethod
    void closeBrowser(){
        DriverPool.quitDriver();
    }
}
