package po;

import driver.DriverPool;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePostPO {

    @FindBy(id = "createPostLink")
    WebElement createPostLink;

    @FindBy(id = "ukrTitleInput")
    WebElement ukrTitleInput;

    @FindBy(id = "ukrShortDescInput")
    WebElement ukrShortDescInput;

    @FindBy(id = "ukrFullDescInput")
    WebElement ukrFullDescInput;

    @FindBy(id = "engTitleInput")
    WebElement engTitleInput;

    @FindBy(id = "engShortDescInput")
    WebElement engShortDescInput;

    @FindBy(id = "engFullDescInput")
    WebElement engFullDescInput;

    @FindBy(id="fileInput")
    WebElement fileInput;

    @FindBy(id = "createPostBtn")
    WebElement createPostBtn;




    public CreatePostPO(){
        PageFactory.initElements(DriverPool.getDriver(), this);
    }

    public WebElement getCreatePostLink() {
        return createPostLink;
    }

    public WebElement getUkrTitleInput() {
        return ukrTitleInput;
    }

    public WebElement getUkrShortDescInput() {
        return ukrShortDescInput;
    }

    public WebElement getUkrFullDescInput() {
        return ukrFullDescInput;
    }

    public WebElement getEngTitleInput() {
        return engTitleInput;
    }

    public WebElement getEngShortDescInput() {
        return engShortDescInput;
    }

    public WebElement getEngFullDescInput() {
        return engFullDescInput;
    }

    public WebElement getFileInput() {
        return fileInput;
    }

    public WebElement getCreatePostBtn() {
        return createPostBtn;
    }
}
