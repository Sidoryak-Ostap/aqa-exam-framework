package po;

import driver.DriverPool;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeletePostPO {

    @FindBy(xpath = "//li[.//div[text()='Ukr title test']]")
    WebElement postToDelete;

    @FindBy(xpath = "//li[.//div[contains(text(), 'Ukr title test')]]//button[contains(text(), 'Видалити')]")
    WebElement deletePostBtn;

    @FindBy(id = "confirmDeleteBtn")
    WebElement confirmDeleteBtn;


    public DeletePostPO(){
        PageFactory.initElements(DriverPool.getDriver(), this);
    }

    public WebElement getPostToDelete(){
        return postToDelete;
    }

    public WebElement getDeletePostBtn(){
        return deletePostBtn;
    }

    public WebElement getConfirmDeleteBtn(){
        return confirmDeleteBtn;
    }
}
