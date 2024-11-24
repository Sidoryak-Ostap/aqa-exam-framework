package bo;

import com.mongodb.client.MongoCollection;
import driver.DriverPool;
import mongo.MongoDb;
import org.bson.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import po.CreatePostPO;

import java.time.Duration;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class CreatePostBO {
    private static final int MAX_WAIT_TIME_MS = 5000;
    private static final int POLLING_INTERVAL_MS = 500;
    private CreatePostPO createPostPO = new CreatePostPO();

    public CreatePostBO navigateToCreatePostPage(){
        WebDriver driver = DriverPool.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement createPostLink = wait.until(ExpectedConditions.visibilityOf(createPostPO.getCreatePostLink()));
        createPostLink.click();
        return this;
    }

    public CreatePostBO focusUkrainianTitle(){
        createPostPO.getUkrTitleInput().click();
        return this;
    }
    public CreatePostBO fillUkrainianTitle(){
        createPostPO.getUkrTitleInput().sendKeys("Ukr title test");
        return this;
    }

    public CreatePostBO focusUkrainianShortDesc(){
        createPostPO.getUkrShortDescInput().click();
        return this;
    }
    public CreatePostBO fillUkrainianShortDesc(){
        createPostPO.getUkrShortDescInput().sendKeys("Ukr full desc test");
        return this;
    }

    public CreatePostBO focusUkrainianFullDesc(){
        createPostPO.getUkrFullDescInput().click();
        return this;
    }
    public CreatePostBO fillUkrainianFullDesc(){
        createPostPO.getUkrFullDescInput().sendKeys("Ukr full desc test");
        return this;
    }

    public CreatePostBO focusEnglishTitle(){
        createPostPO.getEngTitleInput().click();
        return this;
    }
    public CreatePostBO fillEnglishTitle(){
        createPostPO.getEngTitleInput().sendKeys("English title test");
        return this;
    }

    public CreatePostBO focusEnglishShortDesc(){
        createPostPO.getEngShortDescInput().click();
        return this;
    }
    public CreatePostBO fillEnglishShortDesc(){
        createPostPO.getEngShortDescInput().sendKeys("English short desc test");
        return this;
    }

    public CreatePostBO focusEnglishFullDesc(){
        createPostPO.getEngFullDescInput().click();
        return this;
    }
    public CreatePostBO fillEnglishFullDesc(){
        createPostPO.getEngFullDescInput().sendKeys("English full desc test");
        return this;
    }

    public CreatePostBO chooseImage(){
        String filePath = System.getProperty("user.dir") + "/src/main/resources/images/Landscape-Color.jpg";

        createPostPO.getFileInput().sendKeys(filePath);
        return this;
    }

    public CreatePostBO clickCreateBtn(){
        createPostPO.getCreatePostBtn().click();
        return this;
    }

    public CreatePostBO verifyPostCreated(String expectedUkrTitle, String expectedEngTitle){
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
