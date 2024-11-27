package bo.partner;

import com.mongodb.client.MongoCollection;
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


    public PartnerBO navigateToAddPartnerPage(){
        partnerPO.getCreatePartnerLinkPage().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    public PartnerBO focusPartnerNameInput(){
        partnerPO.getPartnerNameInput().focusInput();
        return this;
    }

    public PartnerBO fillPartnerNameInput(String value){
        partnerPO.getPartnerNameInput().fillInput(value);
        return this;
    }

    public PartnerBO focusPartnerWebsiteUrlInput(){
        partnerPO.getPartnerWebsiteUrlInput().focusInput();
        return this;
    }

    public PartnerBO fillPartnerWebsiteUrlInput(String value){
        partnerPO.getPartnerWebsiteUrlInput().fillInput(value);
        return this;
    }

    public PartnerBO fillPartnerImageInput(String image){
        partnerPO.getPartnerImageInput().fillInput(image);
        return this;
    }

    public PartnerBO clickCreatePartnerBtn(){
        partnerPO.getAddNewPartnerBtn().clickBtn();
        return this;
    }

    public PartnerBO closePopUpMessage(){
        partnerPO.getSuccessMessageBtn().waitForButtonToBeVisible().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    public PartnerBO navigateToViewPartnersPage(){
        partnerPO.getViewPartnersPage().waitForLinkToBeClickable().click();
        return this;
    }

    public PartnerBO clickDeletePartnerBtn(){
        partnerPO.getDeletePartnerButton().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    public PartnerBO confirmDeletion(){
        partnerPO.getConfirmDeleteBtn().waitForButtonToBeClickable().clickBtn();
        return this;
    }


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
