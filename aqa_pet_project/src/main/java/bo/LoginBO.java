package bo;

import driver.DriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import po.LoginPO;

import java.time.Duration;

public class LoginBO {

    private LoginPO loginPO = new LoginPO();

    public void login(String username, String password) {
        goToWebsite()
                .openLoginPage()
                .focusLoginInput()
                .fillLoginInput(username)
                .focusPasswordInput()
                .fillPasswordInput(password)
                .clickLoginBtn();
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
        loginPO.getLoginInput().click();
        return this;
    }

    public LoginBO fillLoginInput(String login){
        loginPO.getLoginInput().sendKeys(login);
        return this;
    }

    public LoginBO focusPasswordInput(){
        loginPO.getPasswordInput().isDisplayed();
        loginPO.getPasswordInput().click();
        return this;
    }

    public LoginBO fillPasswordInput(String password){
        loginPO.getPasswordInput().sendKeys(password);
        return this;
    }

    public LoginBO clickLoginBtn(){
        loginPO.getLoginBtn().click();
        return this;
    }

    public LoginBO verifyLogin(String expectedUrl){
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
