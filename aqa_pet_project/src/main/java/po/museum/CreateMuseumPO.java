package po.museum;

import driver.DriverPool;
import element_wrappers.ButtonElement;
import element_wrappers.FieldDecorator;
import element_wrappers.InputElement;
import element_wrappers.LinkElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateMuseumPO {


    @FindBy(id = "createMuseumLink")
    LinkElement createMuseumLink;

    @FindBy(id = "ukrTitleMuseumInput")
    InputElement ukrTitleMuseumInput;

    @FindBy(id = "ukrWorkHrsMuseumInput")
    InputElement ukrWorkHrsMuseumInput;

    @FindBy(id = "ukrAddressMuseumInput")
    InputElement ukrAddressMuseumInput;

    @FindBy(id = "engTitleMuseumInput")
    InputElement engTitleMuseumInput;

    @FindBy(id = "engWorkHrsMuseumInput")
    InputElement engWorkHrsMuseumInput;

    @FindBy(id = "engAddressMuseumInput")
    InputElement engAddressMuseumInput;

    @FindBy(id = "museumLinkInput")
    InputElement museumLinkInput;

    @FindBy(id = "museumPhoneInput")
    InputElement museumPhoneInput;

    @FindBy(id = "museumEmailInput")
    InputElement museumEmailInput;

    @FindBy(id = "addMuseumImage")
    InputElement addMuseumImage;

    @FindBy(id = "createMuseumBtn")
    ButtonElement createMuseumBtn;

    @FindBy(id = "successMessageBtn")
    ButtonElement successMessageBtn;

    public CreateMuseumPO(){
        PageFactory.initElements(new FieldDecorator(DriverPool.getDriver()), this);
    }

    public LinkElement getCreateMuseumLink(){
        return createMuseumLink;
    }

    public InputElement getUkrTitleMuseumInput() {
        return ukrTitleMuseumInput;
    }

    public InputElement getUkrWorkHrsMuseumInput() {
        return ukrWorkHrsMuseumInput;
    }

    public InputElement getUkrAddressMuseumInput() {
        return ukrAddressMuseumInput;
    }

    public InputElement getEngTitleMuseumInput() {
        return engTitleMuseumInput;
    }

    public InputElement getEngWorkHrsMuseumInput() {
        return engWorkHrsMuseumInput;
    }

    public InputElement getEngAddressMuseumInput() {
        return engAddressMuseumInput;
    }

    public InputElement getMuseumLinkInput() {
        return museumLinkInput;
    }

    public InputElement getMuseumPhoneInput() {
        return museumPhoneInput;
    }

    public InputElement getMuseumEmailInput() {
        return museumEmailInput;
    }

    public InputElement getAddMuseumImage() {
        return addMuseumImage;
    }

    public ButtonElement getCreateMuseumBtn() {
        return createMuseumBtn;
    }

    public ButtonElement getSuccessMessageBtn(){
        return successMessageBtn;
    }
}
