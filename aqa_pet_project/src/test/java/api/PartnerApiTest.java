package api;

import api.templates.PartnerTemplate;
import listeners.AllureUIListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners({
        AllureUIListener.class
})
public class PartnerApiTest {

    PartnerApiBO partnerApiBO;

    @BeforeTest
    public void setup(){
        partnerApiBO = new PartnerApiBO();
    }

    @Test
    public void partnerApiTest(){
        // Step 1: Login
        String token = partnerApiBO.login();

        // Step 2: Create partner
        PartnerTemplate createdPartner = partnerApiBO.createPartner(token);
        PartnerTemplate expectedPartner = partnerApiBO.getPartnerFromDB(createdPartner.get_id());

        // Step 3: Verify partner was created and saved
        Assert.assertEquals(createdPartner, expectedPartner);

        // Step 4: Delete post
        PartnerTemplate deletedPartner = partnerApiBO.deletePartner(token, createdPartner.get_id());

        // Step 5: Verify post deleted
        Assert.assertTrue(partnerApiBO.isPartnerDeleted(deletedPartner.get_id()));

    }
}
