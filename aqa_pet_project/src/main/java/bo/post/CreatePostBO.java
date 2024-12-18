package bo.post;

import com.mongodb.client.MongoCollection;
import io.qameta.allure.Step;
import mongo.MongoDb;
import org.bson.Document;
import org.testng.Assert;
import po.post.CreatePostPO;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class CreatePostBO {
    private static final int MAX_WAIT_TIME_MS = 5000;
    private static final int POLLING_INTERVAL_MS = 500;
    private CreatePostPO createPostPO = new CreatePostPO();



    @Step("Navigate to create post page")
    public CreatePostBO navigateToCreatePostPage(){
        createPostPO.getCreatePostLink().waitForLinkToBeVisible().waitForLinkToBeClickable().click();
        return this;
    }

    @Step("Focus ukrainian title input")
    public CreatePostBO focusUkrainianTitle(){
        createPostPO.getUkrTitleInput().focusInput();
        return this;
    }
    @Step("Fill ukrainian title input")
    public CreatePostBO fillUkrainianTitle(String title){
        createPostPO.getUkrTitleInput().fillInput(title);
        return this;
    }

    @Step("Focus ukrainian short description input")
    public CreatePostBO focusUkrainianShortDesc(){
        createPostPO.getUkrShortDescInput().focusInput();
        return this;
    }

    @Step("Fill ukrainian short description input")
    public CreatePostBO fillUkrainianShortDesc(String shortDesc){
        createPostPO.getUkrShortDescInput().fillInput(shortDesc);
        return this;
    }


    @Step("Focus ukrainian full description input")
    public CreatePostBO focusUkrainianFullDesc(){
        createPostPO.getUkrFullDescInput().focusInput();
        return this;
    }

    @Step("Fill ukrainian full description input")
    public CreatePostBO fillUkrainianFullDesc(String fullDesc){
        createPostPO.getUkrFullDescInput().fillInput(fullDesc);
        return this;
    }


    @Step("Focus english title input")
    public CreatePostBO focusEnglishTitle(){
        createPostPO.getEngTitleInput().focusInput();
        return this;
    }

    @Step("Fill english title input")
    public CreatePostBO fillEnglishTitle(String title){
        createPostPO.getEngTitleInput().fillInput(title);
        return this;
    }


    @Step("Focus english short description input")
    public CreatePostBO focusEnglishShortDesc(){
        createPostPO.getEngShortDescInput().focusInput();
        return this;
    }

    @Step("Fill english short description input")
    public CreatePostBO fillEnglishShortDesc(String shortDesc){
        createPostPO.getEngShortDescInput().fillInput(shortDesc);
        return this;
    }


    @Step("Focus english full description input")
    public CreatePostBO focusEnglishFullDesc(){
        createPostPO.getEngFullDescInput().focusInput();
        return this;
    }
    @Step("Fill english full description input")
    public CreatePostBO fillEnglishFullDesc(String fullDesc){
        createPostPO.getEngFullDescInput().fillInput(fullDesc);
        return this;
    }


    @Step("Attach post image")
    public CreatePostBO chooseImage(String image){
        String filePath = System.getProperty("user.dir") + "/src/main/resources/images/Landscape-Color.jpg";

        createPostPO.getFileInput().fillInput(image);
        return this;
    }


    @Step("Click create post button")
    public CreatePostBO clickCreateBtn(){
        createPostPO.getCreatePostBtn().clickBtn();
        return this;
    }


    @Step("Click close popup message button")
    public CreatePostBO closePopUpMessage(){
        createPostPO.getSuccessMessageBtn().waitForButtonToBeVisible().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    @Step("Verify post is created")
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
