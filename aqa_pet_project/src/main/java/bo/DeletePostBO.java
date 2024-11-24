package bo;

import com.mongodb.client.MongoCollection;
import driver.DriverPool;
import mongo.MongoDb;
import org.bson.Document;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import po.DeletePostPO;

import java.time.Duration;

import static com.mongodb.client.model.Filters.eq;

public class DeletePostBO {

    private DeletePostPO deletePostPO = new DeletePostPO();
    private static final int MAX_WAIT_TIME_MS = 5000;
    private static final int POLLING_INTERVAL_MS = 500;

    public DeletePostBO verifyPostToDeleteExists(){
        WebDriverWait wait = new WebDriverWait(DriverPool.getDriver(), Duration.ofSeconds(10));
        WebElement postToDelete = wait.until(ExpectedConditions.visibilityOf(deletePostPO.getPostToDelete()));
        Assert.assertTrue(postToDelete.isDisplayed());
        return this;
    }

    public DeletePostBO clickDeleteBtn(){
        deletePostPO.getDeletePostBtn().click();
        return this;
    }

    public DeletePostBO confirmDelete(){
        deletePostPO.getConfirmDeleteBtn().click();
        return this;
    }

    public DeletePostBO verifyPostDeleted() {
        try {
            MongoDb.init();
            MongoCollection<Document> postsCollection = MongoDb.getPostsCollection();
            long startTime = System.currentTimeMillis();

            Document post = null;
            while (System.currentTimeMillis() - startTime < MAX_WAIT_TIME_MS) {
                post = postsCollection.find(eq("ukrainian.title", "Ukr title test")).first();
                if (post == null) {
                    break;
                }
                Thread.sleep(POLLING_INTERVAL_MS);
            }

        Assert.assertNull(post, "Post has not been deleted");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            MongoDb.closeConnection();
        }
        return this;
    }
}
