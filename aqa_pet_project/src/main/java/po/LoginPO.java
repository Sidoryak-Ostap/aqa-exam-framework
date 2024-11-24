package po;


import driver.DriverPool;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPO {

    @FindBy(id = "adminLogin")
    WebElement loginInput;

    @FindBy(id = "adminPassword")
    WebElement passwordInput;

    @FindBy(id = "loginBtn")
    WebElement loginBtn;

    @FindBy(id = "errorMessage")
    WebElement errorMessage;

    public LoginPO(){
        PageFactory.initElements(DriverPool.getDriver(), this);
    }

    public WebElement getLoginInput() {
        return loginInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }
    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public WebElement getErrorMessage(){
        return errorMessage;
    }

}
