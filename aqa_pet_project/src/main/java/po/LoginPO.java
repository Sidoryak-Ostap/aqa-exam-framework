package po;


import driver.DriverPool;
import element_wrappers.ButtonElement;
import element_wrappers.FieldDecorator;
import element_wrappers.InputElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPO {

    @FindBy(id = "adminLogin")
    InputElement loginInput;

    @FindBy(id = "adminPassword")
    InputElement passwordInput;

    @FindBy(id = "loginBtn")
    ButtonElement loginBtn;

    @FindBy(id = "errorMessage")
    WebElement errorMessage;

    public LoginPO(){
        PageFactory.initElements(new FieldDecorator(DriverPool.getDriver()), this);
    }

    public InputElement getLoginInput() {
        return loginInput;
    }

    public InputElement getPasswordInput() {
        return passwordInput;
    }
    public ButtonElement getLoginBtn() {
        return loginBtn;
    }

    public WebElement getErrorMessage(){
        return errorMessage;
    }

}
