package ui;

import bo.CreatePostBO;
import bo.LoginBO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreatePostTest {



    @BeforeMethod
    public void setup(){
        String login = "admin";
        String password = "admin";
        new LoginBO().login(login, password);
    }

    @Test
    public void createPostTest(){
        // 1 navigate to create post page
        new CreatePostBO().navigateToCreatePostPage()
        // 2 Focus ukrainian title input
                .focusUkrainianTitle()
        // 3 Input ukrainian title
                .fillUkrainianTitle()
        // 4 Focus ukrainian short description
                .focusUkrainianShortDesc()
        // 5 Input ukrainian short description
                .fillUkrainianShortDesc()
        // 6 Focus ukrainian full description
                .focusUkrainianFullDesc()
        // 7 Input ukrainian full description
                .fillUkrainianFullDesc()
        // 8 Focus english title input
                .focusEnglishTitle()
        // 9 Input english title
                .fillEnglishTitle()
        // 10 Focus english short description
                .focusEnglishShortDesc()
        // 11 Input english short description
                .fillEnglishShortDesc()
        // 12 Focus english full description
                .focusEnglishFullDesc()
        // 13 Input english full description
                .fillEnglishFullDesc()
        // 14 Chose image
                .chooseImage()
        // Click create button
                .clickCreateBtn()
        // Verify that post has been created
                .verifyPostCreated("Ukr title test", "English title test");
    }
}
