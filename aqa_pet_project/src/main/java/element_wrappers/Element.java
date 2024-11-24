package element_wrappers;

import org.openqa.selenium.WebElement;

public class Element {

    WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElement getElement(){
        return webElement;
    }
}
