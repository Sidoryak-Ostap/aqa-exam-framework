package api;

import api.templates.PostTemplate;
import listeners.AllureUIListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners({
        AllureUIListener.class
})
public class PostAPITest {

    PostApiBO postApiBO;

    @BeforeTest
    public void setup(){
        postApiBO = new PostApiBO();
    }

    @Test
    public void postApiTest(){

        // Step 1: Login
        String token = postApiBO.login();

        // Step 2: Create Post
        PostTemplate createdPostTemplate = postApiBO.createPost(token);
        PostTemplate expectedPostTemplate = postApiBO.getPostFromDB(createdPostTemplate.get_id());

        // Step 3: Verify post created and saved correctly
        Assert.assertEquals(createdPostTemplate, expectedPostTemplate);

        // Step 4: Update Post
        PostTemplate updatedPostTemplate = postApiBO.updatePost(token, createdPostTemplate.get_id());
        PostTemplate expectedUpdatedPostTemplate = postApiBO.getPostFromDB(updatedPostTemplate.get_id());

        // Step 5: Verify post updated and saved correctly
        Assert.assertEquals(updatedPostTemplate, expectedUpdatedPostTemplate);

        // Step 6: Delete post
        PostTemplate deletedPostTemplate = postApiBO.deletePost(token, updatedPostTemplate.get_id());

        // Step 7: Verify post deleted
        Assert.assertTrue(postApiBO.isPostDeleted(deletedPostTemplate.get_id()), "Post is not deleted");
    }



}
