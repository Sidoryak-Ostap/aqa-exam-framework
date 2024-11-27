package bo.museum;

import com.mongodb.client.MongoCollection;
import io.qameta.allure.Step;
import mongo.MongoDb;
import org.bson.Document;
import org.testng.Assert;
import po.museum.DeleteMuseumPO;

import static com.mongodb.client.model.Filters.eq;

public class DeleteMuseumBO {

    private static final int MAX_WAIT_TIME_MS = 5000;
    private static final int POLLING_INTERVAL_MS = 500;
    DeleteMuseumPO deleteMuseumPO = new DeleteMuseumPO();



    @Step("Navigate to view museum page")
    public DeleteMuseumBO navigateToViewMuseumsPage(){
        deleteMuseumPO.getViewMuseumsLink().click();
        return this;
    }

    @Step("Click delete museum button")
    public DeleteMuseumBO clickDeleteMuseumBtn(){
        deleteMuseumPO.getDeleteMuseumBtn().waitForButtonToBeVisible().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    @Step("Click confirm deletion button")
    public DeleteMuseumBO confirmDeletion(){
        deleteMuseumPO.getConfirmDeleteBtn().clickBtn();
        return this;
    }

    @Step("Click close popup message button")
    public DeleteMuseumBO closePopUpMessage(){
        deleteMuseumPO.getSuccessMessageBtn().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    @Step("Verify museum is deleted")
    public DeleteMuseumBO verifyMuseumDeleted(){
        try {
            MongoDb.init();
            MongoCollection<Document> postsCollection = MongoDb.getMuseumCollection();
            long startTime = System.currentTimeMillis();

            Document museum = null;
            while (System.currentTimeMillis() - startTime < MAX_WAIT_TIME_MS) {
                museum = postsCollection.find(eq("ukrainian.title", "Ukr Museum Name 1")).first();
                if (museum == null) {
                    break;
                }
                Thread.sleep(POLLING_INTERVAL_MS);
            }

            Assert.assertNull(museum, "Museum has not been deleted");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            MongoDb.closeConnection();
        }
        return this;
    }
}
