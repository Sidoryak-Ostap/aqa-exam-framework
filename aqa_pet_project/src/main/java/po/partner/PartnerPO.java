package po.partner;

import driver.DriverPool;
import element_wrappers.ButtonElement;
import element_wrappers.FieldDecorator;
import element_wrappers.InputElement;
import element_wrappers.LinkElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PartnerPO {


    @FindBy(id = "partnerNameInput")
    InputElement partnerNameInput;

    @FindBy(id = "partnerWebsiteUrlInput")
    InputElement partnerWebsiteUrlInput;

    @FindBy(id = "partnerImageInput")
    InputElement partnerImageInput;

    @FindBy(id = "addNewPartner")
    ButtonElement addNewPartnerBtn;

    @FindBy(id = "successMessageBtn")
    ButtonElement successMessageBtn;

    @FindBy(id = "createPartnerLink")
    ButtonElement createPartnerLinkPage;

    @FindBy(id = "viewPartnersLink")
    LinkElement viewPartnersPage;

    @FindBy(xpath = "//div[p[contains(text(), 'Partner 2')]]//button")
    ButtonElement deletePartnerButton;

    @FindBy(id = "confirmDeleteBtn")
    ButtonElement confirmDeleteBtn;






    public PartnerPO(){
        PageFactory.initElements(new FieldDecorator(DriverPool.getDriver()), this);
    }

    public InputElement getPartnerNameInput() {
        return partnerNameInput;
    }

    public InputElement getPartnerWebsiteUrlInput() {
        return partnerWebsiteUrlInput;
    }

    public InputElement getPartnerImageInput() {
        return partnerImageInput;
    }

    public ButtonElement getAddNewPartnerBtn() {
        return addNewPartnerBtn;
    }

    public ButtonElement getSuccessMessageBtn() {
        return successMessageBtn;
    }

    public ButtonElement getCreatePartnerLinkPage() {
        return createPartnerLinkPage;
    }

    public LinkElement getViewPartnersPage(){
        return viewPartnersPage;
    }

    public ButtonElement getDeletePartnerButton(){
        return  deletePartnerButton;
    }

    public ButtonElement getConfirmDeleteBtn(){
        return confirmDeleteBtn;
    }

}
