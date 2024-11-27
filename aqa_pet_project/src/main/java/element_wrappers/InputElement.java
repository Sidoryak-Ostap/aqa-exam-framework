package element_wrappers;

import driver.DriverPool;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InputElement extends Element{

    public InputElement(WebElement webElement){
        super(webElement);
    }

    public InputElement focusInput(){
        super.getElement().click();
        return this;

    }

    public InputElement fillInput(String info){
        super.getElement().sendKeys(info);
        return this;

    }

    public InputElement cleanInput(){
        JavascriptExecutor js = (JavascriptExecutor) DriverPool.getDriver();
        js.executeScript("arguments[0].value = '';", super.getElement());
        return this;
    }

    public InputElement waitForInputToBeVisible(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(super.getElement()));
        return this;
    }

    public InputElement waitForInputToBeClickable(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(super.getElement()));
        return this;
    }
}
