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

    public WebElement waitForLinkToBeVisible(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(4));
        return wait.until(ExpectedConditions.visibilityOf(super.getElement()));
    }

    public void click(){
        super.getElement().click();
    }

}
