package ui;

import bo.login.LoginBO;
import bo.post.CreatePostBO;
import bo.post.DeletePostBO;
import bo.post.UpdatePostBO;
import dataproviders.PostDataProvider;
import driver.DriverPool;
import listeners.AllureUIListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({
        AllureUIListener.class
})
public class PostTest {
    String login = "admin";
    String password = "admin";
    String expectedUrl = "http://localhost:3000/admin/view-posts";


    @Test
    public void loginTest(){
        new LoginBO().login(login, password, expectedUrl);
    }

    @Test(dataProvider = "provideCreatePostData", dataProviderClass = PostDataProvider.class, dependsOnMethods = "loginTest")
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
                // Close message
                .closePopUpMessage()
                // Verify that post has been created
                .verifyPostCreated(ukrTitle, engTitle);
    }


    @Test(dataProvider = "provideUpdatePostData", dataProviderClass = PostDataProvider.class, dependsOnMethods = "createPostTest")
    public void updatePostTest(String ukrTitle, String ukrShortDesc, String ukrFullDesc,
                               String engTitle, String engShortDesc, String engFullDesc,
                               String filePathImage){
        new UpdatePostBO()
                // 1 navigate to view posts page
                .navigateToViewPostsPage()
                // 2 verify post to update is present
                .verifyPostToUpdateExists()
                // 3 Click Update btn on post
                .clickUpdatePostBtn()
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
                .clickUpdateBtn()
                // Close message
                .closePopUpMessage()
                // Verify that post has been created
                .verifyPostUpdated(ukrTitle, engTitle)
                // navigate to view posts page
                .navigateToViewPostsPage();
    }


    @Test(dependsOnMethods = "updatePostTest")
    public void DeletePostTest(){
        new DeletePostBO()
                // 1 Verify post to delete exists
                .verifyPostToDeleteExists()
                // 2 Click on delete button
                .clickDeleteBtn()
                // 3 Confirm delete
                .confirmDelete()
                // 4 Verify post was deleted
                .verifyPostDeleted()
                // 5 Close pop up window
                .closeSuccessMessage();
    }


    @Test(dependsOnMethods = "DeletePostTest")
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
