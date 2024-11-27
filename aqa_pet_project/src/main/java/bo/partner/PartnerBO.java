package bo.partner;

import com.mongodb.client.MongoCollection;
import io.qameta.allure.Step;
import mongo.MongoDb;
import org.bson.Document;
import org.testng.Assert;
import po.partner.PartnerPO;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class PartnerBO {

    PartnerPO partnerPO = new PartnerPO();
    private static final int MAX_WAIT_TIME_MS = 5000;
    private static final int POLLING_INTERVAL_MS = 500;



    @Step("Navigate to add partner page")
    public PartnerBO navigateToAddPartnerPage(){
        partnerPO.getCreatePartnerLinkPage().waitForButtonToBeClickable().clickBtn();
        return this;
    }


    @Step("Focus partner name input")
    public PartnerBO focusPartnerNameInput(){
        partnerPO.getPartnerNameInput().focusInput();
        return this;
    }


    @Step("Fill partner name input")
    public PartnerBO fillPartnerNameInput(String value){
        partnerPO.getPartnerNameInput().fillInput(value);
        return this;
    }


    @Step("Focus partner website url input")
    public PartnerBO focusPartnerWebsiteUrlInput(){
        partnerPO.getPartnerWebsiteUrlInput().focusInput();
        return this;
    }

    @Step("Fill partner website url input")
    public PartnerBO fillPartnerWebsiteUrlInput(String value){
        partnerPO.getPartnerWebsiteUrlInput().fillInput(value);
        return this;
    }


    @Step("Fill partner image input")
    public PartnerBO fillPartnerImageInput(String image){
        partnerPO.getPartnerImageInput().fillInput(image);
        return this;
    }

    @Step("Click create partner button")
    public PartnerBO clickCreatePartnerBtn(){
        partnerPO.getAddNewPartnerBtn().clickBtn();
        return this;
    }


    @Step("Click close popup message button")
    public PartnerBO closePopUpMessage(){
        partnerPO.getSuccessMessageBtn().waitForButtonToBeVisible().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    @Step("Navigate to view partners page")
    public PartnerBO navigateToViewPartnersPage(){
        partnerPO.getViewPartnersPage().waitForLinkToBeClickable().click();
        return this;
    }

    @Step("Click delete partner button")
    public PartnerBO clickDeletePartnerBtn(){
        partnerPO.getDeletePartnerButton().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    @Step("Click confirm deletion partner button")
    public PartnerBO confirmDeletion(){
        partnerPO.getConfirmDeleteBtn().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    @Step("Verify partner was created")
    public PartnerBO verifyPartnerCreated(String partnerName, String partnerUrl){
            try {
                MongoDb.init();
                MongoCollection<Document> partnersCollection = MongoDb.getPartnerCollection();

                Document post = null;
                long startTime = System.currentTimeMillis();

                while (System.currentTimeMillis() - startTime < MAX_WAIT_TIME_MS) {
                    post = partnersCollection.find(
                            and(
                                    eq("name", partnerName),
                                    eq("link", partnerUrl)
                            )
                    ).first();
                    if (post != null) {
                        break;
                    }

                    Thread.sleep(POLLING_INTERVAL_MS);
                }

                Assert.assertNotNull(
                        post,
                        String.format("Partner not found with name '%s' and url '%s'", partnerName, partnerUrl)
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


    @Step("Verify partner was deleted")
    public PartnerBO verifyPartnerDeleted(String partnerName){
            try {
                MongoDb.init();
                MongoCollection<Document> postsCollection = MongoDb.getPostsCollection();
                long startTime = System.currentTimeMillis();

                Document post = null;
                while (System.currentTimeMillis() - startTime < MAX_WAIT_TIME_MS) {
                    post = postsCollection.find(eq("name", partnerName)).first();
                    if (post == null) {
                        break;
                    }
                    Thread.sleep(POLLING_INTERVAL_MS);
                }

                Assert.assertNull(post, "Partner has not been deleted");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                MongoDb.closeConnection();
            }
            return this;
    }
}
