package listeners;

import driver.DriverPool;
import io.qameta.allure.Attachment;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureUIListener implements ITestListener, IInvokedMethodListener {

    private static final Logger logger = LogManager.getLogger(AllureUIListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: {}#{}", result.getTestClass().getName(), result.getMethod().getMethodName());
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}#{}", result.getTestClass().getName(), result.getMethod().getMethodName());
        ITestListener.super.onTestSuccess(result);
        makeScreenShot();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}#{}", result.getTestClass().getName(), result.getMethod().getMethodName());
        logger.error("Error: {}", result.getThrowable().getMessage(), result.getThrowable());
        ITestListener.super.onTestFailure(result);
        makeScreenShot();
        attachDOM();
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.info("After method invocation: {}#{}", method.getTestMethod().getTestClass().getName(), method.getTestMethod().getMethodName());
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test finished successfully: {}", method.getTestMethod().getMethodName());
        } else if (testResult.getStatus() == ITestResult.FAILURE) {
            logger.error("Test failed: {}", method.getTestMethod().getMethodName());
        }
        IInvokedMethodListener.super.afterInvocation(method, testResult);
        attachDOM();
        makeScreenShot();
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] makeScreenShot() {
        try {
            logger.info("Capturing screenshot...");
            return ((TakesScreenshot) DriverPool.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            return new byte[0];
        }
    }

    @Attachment(value = "Page DOM", type = "text/html")
    private String attachDOM() {
        try {
            logger.info("Capturing page DOM...");
            return DriverPool.getDriver().getPageSource();
        } catch (Exception e) {
            logger.error("Failed to capture DOM: {}", e.getMessage());
            return "Failed to capture DOM.";
        }
    }

    @Attachment(value = "API Request", type = "text/plain")
    public static String attachAPIRequest(String request) {
        logger.info("Attaching API Request...");
        return request;
    }

    @Attachment(value = "API Response", type = "text/plain")
    public static String attachAPIResponse(Response response) {
        logger.info("Attaching API Response...");
        return response.prettyPrint();
    }



}
