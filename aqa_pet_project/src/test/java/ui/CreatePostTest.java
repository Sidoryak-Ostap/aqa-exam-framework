package ui;

import bo.CreatePostBO;
import bo.LoginBO;
import dataproviders.PostDataProvider;
import driver.DriverPool;
import listeners.AllureListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({
        AllureListener.class
})
public class CreatePostTest {
    @BeforeMethod
    public void setup(){
        String login = "admin";
        String password = "admin";
        new LoginBO().login(login, password);
    }

    @Test(dataProvider = "provideCreatePostData", dataProviderClass = PostDataProvider.class)
    public void createPostTest(String ukrTitle, String ukrShortDesc, String ukrFullDesc,
                               String engTitle, String engShortDesc, String engFullDesc,
                               String filePathImage){
        // 1 navigate to create post page
        new CreatePostBO().navigateToCreatePostPage()
        // 2 Focus ukrainian title input
                .focusUkrainianTitle()
        // 3 Input ukrainian title
                .fillUkrainianTitle(ukrTitle)
        // 4 Focus ukrainian short description
                .focusUkrainianShortDesc()
        // 5 Input ukrainian short description
                .fillUkrainianShortDesc(ukrShortDesc)
        // 6 Focus ukrainian full description
                .focusUkrainianFullDesc()
        // 7 Input ukrainian full description
                .fillUkrainianFullDesc(ukrFullDesc)
        // 8 Focus english title input
                .focusEnglishTitle()
        // 9 Input english title
                .fillEnglishTitle(engTitle)
        // 10 Focus english short description
                .focusEnglishShortDesc()
        // 11 Input english short description
                .fillEnglishShortDesc(engShortDesc)
        // 12 Focus english full description
                .focusEnglishFullDesc()
        // 13 Input english full description
                .fillEnglishFullDesc(engFullDesc)
        // 14 Chose image
                .chooseImage(filePathImage)
        // Click create button
                .clickCreateBtn()
        // Verify that post has been created
                .verifyPostCreated(ukrTitle, engTitle);
    }

    @AfterMethod
    void closeBrowser(){
        DriverPool.quitDriver();
    }
}
