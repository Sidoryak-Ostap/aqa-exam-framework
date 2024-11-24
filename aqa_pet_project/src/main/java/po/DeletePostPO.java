package po;

import driver.DriverPool;
import element_wrappers.ButtonElement;
import element_wrappers.FieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeletePostPO {

    @FindBy(xpath = "//li[.//div[text()='Ukr title test']]")
    WebElement postToDelete;

    @FindBy(xpath = "//li[.//div[contains(text(), 'Ukr title test')]]//button[contains(text(), 'Видалити')]")
    ButtonElement deletePostBtn;

    @FindBy(id = "confirmDeleteBtn")
    ButtonElement confirmDeleteBtn;


    public DeletePostPO(){
        PageFactory.initElements(new FieldDecorator(DriverPool.getDriver()), this);
    }

    public WebElement getPostToDelete(){
        return postToDelete;
    }

    public ButtonElement getDeletePostBtn(){
        return deletePostBtn;
    }

    public ButtonElement getConfirmDeleteBtn(){
        return confirmDeleteBtn;
    }
}
