package element_wrappers;

import org.openqa.selenium.WebElement;

public class InputElement extends Element{

    public InputElement(WebElement webElement){
        super(webElement);
    }

    public void focusInput(){
        super.getElement().click();
    }

    public void fillInput(String info){
        super.getElement().sendKeys(info);
    }
}
