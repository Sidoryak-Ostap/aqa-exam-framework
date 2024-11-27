package ui;

import bo.login.LoginBO;
import bo.museum.CreateMuseumBO;
import bo.museum.DeleteMuseumBO;
import dataproviders.MuseumDataProvider;
import driver.DriverPool;
import listeners.AllureUIListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({
        AllureUIListener.class
})
public class MuseumTest {

    String login = "admin";
    String password = "admin";
    String expectedUrl = "http://localhost:3000/admin/view-posts";


    @Test
    public void loginTest(){
        new LoginBO().login(login, password, expectedUrl);
    }

    @Test(dependsOnMethods = "loginTest", dataProvider = "provideCreateMuseumData", dataProviderClass = MuseumDataProvider.class)
    public void createMuseum(String ukrTitle, String ukrWorkHrs,
                             String ukrAddress, String engTitle,
                             String engWorkHrs, String engAddress,
                             String museumLink, String museumPhone,
                             String museumEmail, String image){
        new CreateMuseumBO()
                // 1 Navigate to createMuseumPage
                .navigateToCreateMuseumPage()
                // 2 Fill ukrainian museum title
                .focusAndFillUkrTitle(ukrTitle)
                // 3 Fill ukrainian work hrs
                .focusAndFillUkrWorkHrs(ukrWorkHrs)
                // 4 Fill ukrainian address
                .focusAndFillUkrAddress(ukrAddress)
                // 5 Fill eng museum title
                .focusAndFillEngTitle(engTitle)
                // 6 Fill eng  work hrs
                .focusAndFillEngAddress(engWorkHrs)
                // 7 Fill eng address
                .focusAndFillEngWorkHrs(engAddress)
                // 8 Fill museum link
                .focusAndFillMuseumLink(museumLink)
                // 9 Fill museum phone
                .focusAndFillMuseumPhone(museumPhone)
                // 10 Fill museum email
                .focusAndFillEmail(museumEmail)
                // 11 Fill museum image
                .fillImageInput(image)
                // 12 Click create museum btn
                .clickCreateMuseumBtn()
                // 13 Close pop up message
                .closePupUpMessage()
                // 14 Verify museum created
                .verifyMuseumCreated(ukrTitle, engTitle);
    }


    @Test(dependsOnMethods = "createMuseum")
    public void deleteMuseumTest(){
        new DeleteMuseumBO()
                .navigateToViewMuseumsPage()
                .clickDeleteMuseumBtn()
                .confirmDeletion()
                .closePopUpMessage()
                .verifyMuseumDeleted();

    }

    @Test(dependsOnMethods = "deleteMuseumTest")
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
