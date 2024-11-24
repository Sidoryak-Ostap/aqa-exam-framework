package po;

import driver.DriverPool;
import element_wrappers.ButtonElement;
import element_wrappers.FieldDecorator;
import element_wrappers.InputElement;
import element_wrappers.LinkElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePostPO {

    @FindBy(id = "createPostLink")
    LinkElement createPostLink;

    @FindBy(id = "ukrTitleInput")
    InputElement ukrTitleInput;

    @FindBy(id = "ukrShortDescInput")
    InputElement ukrShortDescInput;

    @FindBy(id = "ukrFullDescInput")
    InputElement ukrFullDescInput;

    @FindBy(id = "engTitleInput")
    InputElement engTitleInput;

    @FindBy(id = "engShortDescInput")
    InputElement engShortDescInput;

    @FindBy(id = "engFullDescInput")
    InputElement engFullDescInput;

    @FindBy(id="fileInput")
    InputElement fileInput;

    @FindBy(id = "createPostBtn")
    ButtonElement createPostBtn;




    public CreatePostPO(){
        PageFactory.initElements(new FieldDecorator(DriverPool.getDriver()), this);
    }

    public LinkElement getCreatePostLink() {
        return createPostLink;
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

    public ButtonElement getCreatePostBtn() {
        return createPostBtn;
    }
}
