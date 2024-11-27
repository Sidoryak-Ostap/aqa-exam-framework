package bo.post;

import com.mongodb.client.MongoCollection;
import driver.DriverPool;
import io.qameta.allure.Step;
import mongo.MongoDb;
import org.bson.Document;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import po.post.UpdatePostPO;

import java.time.Duration;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class UpdatePostBO {

    private static final int MAX_WAIT_TIME_MS = 5000;
    private static final int POLLING_INTERVAL_MS = 500;
    UpdatePostPO updatePostPO = new UpdatePostPO();

    @Step("Navigate to update post page")
    public UpdatePostBO navigateToViewPostsPage(){
        updatePostPO.getViewPostsLink().waitForLinkToBeVisible().click();
        return this;
    }

    @Step("Verify post to update exists")
    public UpdatePostBO verifyPostToUpdateExists(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(10));
        WebElement postToUpdate = wait.until(ExpectedConditions.visibilityOf(updatePostPO.getPostToUpdate()));
        Assert.assertTrue(postToUpdate.isDisplayed());
        return this;
    }


    @Step("Click update post button")
    public UpdatePostBO clickUpdatePostBtn(){
        updatePostPO.getUpdatePostBtn().waitForLinkToBeVisible().waitForLinkToBeClickable().click();
        return this;
    }

    @Step("Focus ukrainian title input")
    public UpdatePostBO focusUkrainianTitle(){
        updatePostPO.getUkrTitleInput().waitForInputToBeVisible().waitForInputToBeClickable().focusInput().cleanInput();
        return this;
    }


    @Step("Fill ukrainian title input")
    public UpdatePostBO fillUkrainianTitle(String title){
        updatePostPO.getUkrTitleInput().fillInput(title);
        return this;
    }


    @Step("Focus ukrainian short description input")
    public UpdatePostBO focusUkrainianShortDesc(){
        updatePostPO.getUkrShortDescInput().focusInput().cleanInput();
        return this;
    }

    @Step("Fill ukrainian short description input")
    public UpdatePostBO fillUkrainianShortDesc(String shortDesc){
        updatePostPO.getUkrShortDescInput().fillInput(shortDesc);
        return this;
    }


    @Step("Focus ukrainian full description input")
    public UpdatePostBO focusUkrainianFullDesc(){
        updatePostPO.getUkrFullDescInput().focusInput().cleanInput();
        return this;
    }

    @Step("Fill ukrainian full description input")
    public UpdatePostBO fillUkrainianFullDesc(String fullDesc){
        updatePostPO.getUkrFullDescInput().fillInput(fullDesc);
        return this;
    }

    @Step("Focus english title input")
    public UpdatePostBO focusEnglishTitle(){
        updatePostPO.getEngTitleInput().focusInput().cleanInput();
        return this;
    }

    @Step("Fill english title input")
    public UpdatePostBO fillEnglishTitle(String title){
        updatePostPO.getEngTitleInput().fillInput(title);
        return this;
    }


    @Step("Focus english short description input")
    public UpdatePostBO focusEnglishShortDesc(){
        updatePostPO.getEngShortDescInput().focusInput().cleanInput();
        return this;
    }

    @Step("Fill english short description input")
    public UpdatePostBO fillEnglishShortDesc(String shortDesc){
        updatePostPO.getEngShortDescInput().fillInput(shortDesc);
        return this;
    }


    @Step("Focus english full description input")
    public UpdatePostBO focusEnglishFullDesc(){
        updatePostPO.getEngFullDescInput().focusInput().cleanInput();
        return this;
    }

    @Step("Fill english full description input")
    public UpdatePostBO fillEnglishFullDesc(String fullDesc){
        updatePostPO.getEngFullDescInput().fillInput(fullDesc);
        return this;
    }

    @Step("Attach post image")
    public UpdatePostBO chooseImage(String image){
        updatePostPO.getFileInput().fillInput(image);
        return this;
    }


    @Step("Click update post button")
    public UpdatePostBO clickUpdateBtn(){
        updatePostPO.getUpdatePostInfoBtn().clickBtn();
        return this;
    }

    @Step("Click close popup message button")
    public UpdatePostBO closePopUpMessage(){
        updatePostPO.getSuccessMessageBtn().waitForButtonToBeVisible().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    @Step("Verify post is updated")
    public UpdatePostBO verifyPostUpdated(String expectedUkrTitle, String expectedEngTitle){
        try {
            MongoDb.init();
            MongoCollection<Document> postsCollection = MongoDb.getPostsCollection();

            Document post = null;
            long startTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - startTime < MAX_WAIT_TIME_MS) {
                post = postsCollection.find(
                        and(
                                eq("ukrainian.title", expectedUkrTitle),
                                eq("english.title", expectedEngTitle)
                        )
                ).first();
                if (post != null) {
                    break;
                }

                Thread.sleep(POLLING_INTERVAL_MS);
            }

            Assert.assertNotNull(
                    post,
                    String.format("Post not found with Ukrainian title '%s' and English title '%s'", expectedUkrTitle, expectedEngTitle)
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for the post to be created", e);
        }
        finally {
            MongoDb.closeConnection();
        }
        return this;
    }



}
