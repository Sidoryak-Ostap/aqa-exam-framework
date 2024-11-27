package po.museum;

import driver.DriverPool;
import element_wrappers.ButtonElement;
import element_wrappers.FieldDecorator;
import element_wrappers.LinkElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteMuseumPO {

    @FindBy(xpath = "//li[.//div[text()='Ukr Museum Name 1']]")
    WebElement museumToDelete;

    @FindBy(xpath = "//li[.//div[contains(text(), 'Ukr Museum Name 1')]]//button[contains(text(), 'Видалити')]")
    ButtonElement deleteMuseumBtn;

    @FindBy(id = "confirmDeleteBtn")
    ButtonElement confirmDeleteBtn;

    @FindBy(id = "successMessageBtn")
    ButtonElement successMessageBtn;


    @FindBy(id = "viewMuseumsLink")
    LinkElement viewMuseumsLink;

    public DeleteMuseumPO(){
        PageFactory.initElements(new FieldDecorator(DriverPool.getDriver()), this);
    }

    public WebElement getMuseumToDelete() {
        return museumToDelete;
    }

    public ButtonElement getDeleteMuseumBtn() {
        return deleteMuseumBtn;
    }

    public ButtonElement getConfirmDeleteBtn() {
        return confirmDeleteBtn;
    }

    public ButtonElement getSuccessMessageBtn() {
        return successMessageBtn;
    }

    public LinkElement getViewMuseumsLink(){
        return viewMuseumsLink;
    }
}
