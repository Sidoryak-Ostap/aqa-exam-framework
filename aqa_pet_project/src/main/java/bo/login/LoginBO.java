package bo.login;

import driver.DriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import po.login.LoginPO;

import java.time.Duration;

public class LoginBO {

    private LoginPO loginPO = new LoginPO();

    public void login(String username, String password, String expectedUrl) {
        goToWebsite()
                .openLoginPage()
                .focusLoginInput()
                .fillLoginInput(username)
                .focusPasswordInput()
                .fillPasswordInput(password)
                .clickLoginBtn()
                .verifyLogin(expectedUrl);
    }
    public LoginBO goToWebsite() {
        DriverPool.getDriver().get("http://localhost:3000/");
        return this;
    }

    public LoginBO openLoginPage(){
        DriverPool.getDriver().get("http://localhost:3000/admin/auth");
        return this;
    }

    public LoginBO focusLoginInput(){
        loginPO.getLoginInput().focusInput();
        return this;
    }

    public LoginBO fillLoginInput(String login){
        loginPO.getLoginInput().fillInput(login);
        return this;
    }

    public LoginBO focusPasswordInput(){
        loginPO.getPasswordInput().focusInput();
        return this;
    }

    public LoginBO fillPasswordInput(String password){
        loginPO.getPasswordInput().fillInput(password);
        return this;
    }

    public LoginBO clickLoginBtn(){
        loginPO.getLoginBtn().clickBtn();
        return this;
    }

    public LoginBO clickLogoutBtn(){
        loginPO.getLogoutBtn().waitForLinkToBeClickable().click();
        return this;
    }

    public LoginBO verifyLogin(String expectedUrl){
        WebDriver driver = DriverPool.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }

    public LoginBO verifyLogout(String expectedUrl){
        WebDriver driver = DriverPool.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }

    public LoginBO verifyFailedLogin(String expectedErrorMessage){

        WebDriver driver = DriverPool.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOf(loginPO.getErrorMessage()));

        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
        return this;
    }
}
