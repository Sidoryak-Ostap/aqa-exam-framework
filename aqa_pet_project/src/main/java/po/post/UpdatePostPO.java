package po.post;

import driver.DriverPool;
import element_wrappers.ButtonElement;
import element_wrappers.FieldDecorator;
import element_wrappers.InputElement;
import element_wrappers.LinkElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdatePostPO {

    @FindBy(id = "viewPostsLink")
    LinkElement viewPostsLink;

    @FindBy(xpath = "//li[.//div[contains(text(), 'Ukr Title')]]")
    WebElement postToUpdate;

    @FindBy(xpath = "//li[.//div[contains(text(), 'Ukr Title')]]//a[contains(text(), 'Редагувати')]")
    LinkElement updatePostLinkBtn;


    @FindBy(id = "ukrTitleUpdateInput")
    InputElement ukrTitleInput;

    @FindBy(id = "ukrShortDescUpdateInput")
    InputElement ukrShortDescInput;

    @FindBy(id = "ukrFullDescUpdateInput")
    InputElement ukrFullDescInput;

    @FindBy(id = "engTitleUpdateInput")
    InputElement engTitleInput;

    @FindBy(id = "engShortDescUpdateInput")
    InputElement engShortDescInput;

    @FindBy(id = "engFullDescUpdateInput")
    InputElement engFullDescInput;

    @FindBy(id="addImageUpdateInput")
    InputElement fileInput;

    @FindBy(id = "updatePostBtn")
    ButtonElement updatePostInfoBtn;

    @FindBy(id = "successMessageBtn")
    ButtonElement successMessageBtn;



    public UpdatePostPO(){
        PageFactory.initElements(new FieldDecorator(DriverPool.getDriver()), this);
    }

    public LinkElement getViewPostsLink() {
        return viewPostsLink;
    }

    public WebElement getPostToUpdate() {
        return postToUpdate;
    }

    public LinkElement getUpdatePostBtn() {
        return updatePostLinkBtn;
    }

    public InputElement getUkrTitleInput() {
        return ukrTitleInput;
    }

    public InputElement getUkrShortDescInput() {
        return ukrShortDescInput;
    }

    public InputElement getUkrFullDescInput() {
        return ukrFullDescInput;
    }

    public InputElement getEngTitleInput() {
        return engTitleInput;
    }

    public InputElement getEngShortDescInput() {
        return engShortDescInput;
    }

    public InputElement getEngFullDescInput() {
        return engFullDescInput;
    }

    public InputElement getFileInput() {
        return fileInput;
    }

    public ButtonElement getUpdatePostInfoBtn() {
        return updatePostInfoBtn;
    }

    public ButtonElement getSuccessMessageBtn() {
        return successMessageBtn;
    }

}
