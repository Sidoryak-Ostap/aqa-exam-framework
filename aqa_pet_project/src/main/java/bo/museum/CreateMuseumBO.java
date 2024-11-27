package bo.museum;

import com.mongodb.client.MongoCollection;
import mongo.MongoDb;
import org.bson.Document;
import org.testng.Assert;
import po.museum.CreateMuseumPO;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class CreateMuseumBO {

    private static final int MAX_WAIT_TIME_MS = 5000;
    private static final int POLLING_INTERVAL_MS = 500;

    CreateMuseumPO createMuseumPO = new CreateMuseumPO();

    public CreateMuseumBO navigateToCreateMuseumPage(){
        createMuseumPO.getCreateMuseumLink().waitForLinkToBeClickable().click();
        return this;
    }

    public CreateMuseumBO focusAndFillUkrTitle(String value){
        createMuseumPO.getUkrTitleMuseumInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO focusAndFillUkrWorkHrs(String value){
        createMuseumPO.getUkrWorkHrsMuseumInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO focusAndFillUkrAddress(String value){
        createMuseumPO.getUkrAddressMuseumInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO focusAndFillEngTitle(String value){
        createMuseumPO.getEngTitleMuseumInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO focusAndFillEngWorkHrs(String value){
        createMuseumPO.getEngWorkHrsMuseumInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO focusAndFillEngAddress(String value){
        createMuseumPO.getEngAddressMuseumInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO focusAndFillMuseumLink(String value){
        createMuseumPO.getMuseumLinkInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO focusAndFillMuseumPhone(String value){
        createMuseumPO.getMuseumPhoneInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO focusAndFillEmail(String value){
        createMuseumPO.getMuseumEmailInput().focusInput().fillInput(value);
        return this;
    }

    public CreateMuseumBO fillImageInput(String image){
        createMuseumPO.getAddMuseumImage().fillInput(image);
        return this;
    }

    public CreateMuseumBO clickCreateMuseumBtn(){
        createMuseumPO.getCreateMuseumBtn().clickBtn();
        return this;
    }

    public CreateMuseumBO closePupUpMessage(){
        createMuseumPO.getSuccessMessageBtn().waitForButtonToBeVisible().waitForButtonToBeClickable().clickBtn();
        return this;
    }

    public CreateMuseumBO verifyMuseumCreated(String expectedUkrTitle, String expectedEngTitle){
        try {
            MongoDb.init();
            MongoCollection<Document> postsCollection = MongoDb.getMuseumCollection();

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
                    String.format("Museum not found with Ukrainian title '%s' and English title '%s'", expectedUkrTitle, expectedEngTitle)
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
