package element_wrappers;


import driver.DriverPool;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ButtonElement extends Element{

    public ButtonElement(WebElement webElement){
        super(webElement);
    }

    public void clickBtn(){
        super.getElement().click();
    }

    public ButtonElement waitForButtonToBeVisible(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(super.getElement()));
        return this;
    }

    public ButtonElement waitForButtonToBeClickable(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(super.getElement()));
        return this;
    }
}
