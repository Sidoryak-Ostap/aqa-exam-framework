package element_wrappers;

import driver.DriverPool;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LinkElement extends Element{

    public LinkElement(WebElement webElement){
        super(webElement);
    }

    public LinkElement waitForLinkToBeVisible(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOf(super.getElement()));
        return this;
    }

    public LinkElement waitForLinkToBeClickable(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(super.getElement()));
        return this;
    }

    public LinkElement click(){
        super.getElement().click();
        return this;
    }

}
