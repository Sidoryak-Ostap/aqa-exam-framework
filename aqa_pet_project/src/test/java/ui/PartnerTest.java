package ui;

import bo.login.LoginBO;
import bo.partner.PartnerBO;
import dataproviders.PartnerDataProvider;
import driver.DriverPool;
import listeners.AllureUIListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({
        AllureUIListener.class
})
public class PartnerTest {

    String login = "admin";
    String password = "admin";
    String expectedUrl = "http://localhost:3000/admin/view-posts";


    @Test
    public void loginTest(){
        new LoginBO().login(login, password, expectedUrl);
    }

    @Test(dependsOnMethods = "loginTest", dataProvider = "provideCreatePartnerData", dataProviderClass = PartnerDataProvider.class)
    public void createPartnerTest(String partnerName, String partnerUrl, String filePathImage){
        new PartnerBO()

                // 1 Navigate to add partner page
                .navigateToAddPartnerPage()
                // 2 Focus partner name input
                .focusPartnerNameInput()
                // 3 Fill partner name input
                .fillPartnerNameInput(partnerName)
                // 4 Focus partner website url input
                .focusPartnerWebsiteUrlInput()
                // 5 Fill partner website url input
                .fillPartnerWebsiteUrlInput(partnerUrl)
                // 6 Add image
                .fillPartnerImageInput(filePathImage)
                // 7 Click create post button
                .clickCreatePartnerBtn()
                // 8 Close pop up windows
                .closePopUpMessage()
                // 9 Verify partner was created
                .verifyPartnerCreated(partnerName, partnerUrl);
    }


    @Test(dependsOnMethods = "createPartnerTest")
    public void deletePartnerTest(){
        new PartnerBO().
                // 1 Navigate to view partners page
                navigateToViewPartnersPage()
                // 2 Click delete partner button
                .clickDeletePartnerBtn()
                // 3 Confirm deletion
                .confirmDeletion()
                // 4 Close pop up message
                .closePopUpMessage()
                // 5 Verify partner deleted
                .verifyPartnerDeleted("Partner 2");
    }

    @Test(dependsOnMethods = "deletePartnerTest")
    public void logoutTest(){
        String expectedLogoutUrl = "http://localhost:3000/";

        new LoginBO()
                // Logout
                .clickLogoutBtn()
                // Verify logout
                .verifyLogout(expectedLogoutUrl);
    }

    @AfterTest
    void closeBrowser(){
        DriverPool.quitDriver();
    }
}



