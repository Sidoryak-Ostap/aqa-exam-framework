package bo.login;

import driver.DriverPool;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
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

    @Step("Open website")
    public LoginBO goToWebsite() {
        DriverPool.getDriver().get("http://localhost:3000/");
        return this;
    }

    @Step("Navigate to sign in page")
    public LoginBO openLoginPage(){
        DriverPool.getDriver().get("http://localhost:3000/admin/auth");
        return this;
    }

    @Step("Focus login input")
    public LoginBO focusLoginInput(){
        loginPO.getLoginInput().focusInput();
        return this;
    }

    @Step("Fill login input")
    public LoginBO fillLoginInput(String login){
        loginPO.getLoginInput().fillInput(login);
        return this;
    }

    @Step("Focus password input")
    public LoginBO focusPasswordInput(){
        loginPO.getPasswordInput().focusInput();
        return this;
    }

    @Step("Fill password input")
    public LoginBO fillPasswordInput(String password){
        loginPO.getPasswordInput().fillInput(password);
        return this;
    }

    @Step("Click login button")
    public LoginBO clickLoginBtn(){
        loginPO.getLoginBtn().clickBtn();
        return this;
    }

    @Step("Click logout button")
    public LoginBO clickLogoutBtn(){
        loginPO.getLogoutBtn().waitForLinkToBeClickable().click();
        return this;
    }

    @Step("Verify login")
    public LoginBO verifyLogin(String expectedUrl){
        WebDriver driver = DriverPool.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }

    @Step("Verify logout")
    public LoginBO verifyLogout(String expectedUrl){
        WebDriver driver = DriverPool.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }


}
