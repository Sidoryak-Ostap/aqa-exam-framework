package element_wrappers;


import org.openqa.selenium.WebElement;

public class ButtonElement extends Element{

    public ButtonElement(WebElement webElement){
        super(webElement);
    }

    public void clickBtn(){
        super.getElement().click();
    }
}
