package bo;

import com.mongodb.client.MongoCollection;
import driver.DriverPool;
import mongo.MongoDb;
import org.bson.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import po.CreatePostPO;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class CreatePostBO {
    private static final int MAX_WAIT_TIME_MS = 5000;
    private static final int POLLING_INTERVAL_MS = 500;
    private CreatePostPO createPostPO = new CreatePostPO();

    public CreatePostBO navigateToCreatePostPage(){
        WebDriver driver = DriverPool.getDriver();

        WebElement createPostLink = createPostPO.getCreatePostLink().waitForLinkToBeVisible();
        createPostLink.click();
        return this;
    }

    public CreatePostBO focusUkrainianTitle(){
        createPostPO.getUkrTitleInput().focusInput();
        return this;
    }
    public CreatePostBO fillUkrainianTitle(String title){
        createPostPO.getUkrTitleInput().fillInput(title);
        return this;
    }

    public CreatePostBO focusUkrainianShortDesc(){
        createPostPO.getUkrShortDescInput().focusInput();
        return this;
    }
    public CreatePostBO fillUkrainianShortDesc(String shortDesc){
        createPostPO.getUkrShortDescInput().fillInput(shortDesc);
        return this;
    }

    public CreatePostBO focusUkrainianFullDesc(){
        createPostPO.getUkrFullDescInput().focusInput();
        return this;
    }
    public CreatePostBO fillUkrainianFullDesc(String fullDesc){
        createPostPO.getUkrFullDescInput().fillInput(fullDesc);
        return this;
    }

    public CreatePostBO focusEnglishTitle(){
        createPostPO.getEngTitleInput().focusInput();
        return this;
    }
    public CreatePostBO fillEnglishTitle(String title){
        createPostPO.getEngTitleInput().fillInput(title);
        return this;
    }

    public CreatePostBO focusEnglishShortDesc(){
        createPostPO.getEngShortDescInput().focusInput();
        return this;
    }
    public CreatePostBO fillEnglishShortDesc(String shortDesc){
        createPostPO.getEngShortDescInput().fillInput(shortDesc);
        return this;
    }

    public CreatePostBO focusEnglishFullDesc(){
        createPostPO.getEngFullDescInput().focusInput();
        return this;
    }
    public CreatePostBO fillEnglishFullDesc(String fullDesc){
        createPostPO.getEngFullDescInput().fillInput(fullDesc);
        return this;
    }

    public CreatePostBO chooseImage(String image){
        String filePath = System.getProperty("user.dir") + "/src/main/resources/images/Landscape-Color.jpg";

        createPostPO.getFileInput().fillInput(image);
        return this;
    }

    public CreatePostBO clickCreateBtn(){
        createPostPO.getCreatePostBtn().clickBtn();
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
