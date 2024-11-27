package api;

import api.templates.MuseumTemplate;
import listeners.AllureUIListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({
        AllureUIListener.class
})
public class MuseumApiTest {

    MuseumApiBO museumApiBO;

    @BeforeTest
    public void setup(){
        museumApiBO = new MuseumApiBO();
    }


    @Test
    public void MuseumApiTest(){
        // Step 1: Login
        String token = museumApiBO.login();

        // Step 2: Create Museum
        MuseumTemplate createdMuseumTemplate = museumApiBO.createMuseum(token);
        MuseumTemplate expectedMuseumTemplate = museumApiBO.getMuseumFromDB(createdMuseumTemplate.get_id());

        // Step 3: Verify museum created and saved correctly
        Assert.assertEquals(createdMuseumTemplate, expectedMuseumTemplate);

        // Step 4: Update Museum
        MuseumTemplate updatedMuseumTemplate = museumApiBO.updateMuseum(token, createdMuseumTemplate.get_id());
        MuseumTemplate expectedUpdatedMuseumTemplate = museumApiBO.getMuseumFromDB(updatedMuseumTemplate.get_id());

        // Step 5: Verify museum updated and saved correctly
        Assert.assertEquals(updatedMuseumTemplate, expectedUpdatedMuseumTemplate);

        // Step 6: Delete museum
        MuseumTemplate deletedMuseumTemplate = museumApiBO.deleteMuseum(token, updatedMuseumTemplate.get_id());

        // Step 7: Verify museum deleted
        Assert.assertTrue(museumApiBO.isMuseumDeleted(deletedMuseumTemplate.get_id()), "Museum is not deleted");
}

}
