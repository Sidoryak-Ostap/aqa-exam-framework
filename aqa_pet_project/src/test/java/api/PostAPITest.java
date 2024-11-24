package api;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PostAPITest {

    PostApiBO postApiBO;

    @BeforeTest
    public void setup(){
        postApiBO = new PostApiBO();
    }

    @Test
    public void createPostAPITest(){
        // Step 1: Login and retrieve token
        String token = postApiBO.login();

        // Step 2: Create Post
        PostTemplate actualPostTemplate = postApiBO.createPost(token);

        // Step 3: Get created Post from database
        PostTemplate expectedPostTemplate = postApiBO.getPostFromDB(actualPostTemplate.get_id());

        // Step 4: Verify result
        Assert.assertEquals(actualPostTemplate, expectedPostTemplate, "Mismatch between API response and database entry.");
    }

    @Test
    public void updatePostAPITest(){
        String postId = "67439aec276dbb732c44d029";
        // Step 1: Login and retrieve token
        String token = postApiBO.login();

        // Step 2: Update Post
        PostTemplate updatedPostTemplate = postApiBO.updatePost(token, postId);

        // Step 3: Get updated Post from database
        PostTemplate expectedPostTemplate = postApiBO.getPostFromDB(postId);

        // Step 4: Verify result
        Assert.assertEquals(updatedPostTemplate, expectedPostTemplate, "Mismatch between API response and database entry.");
    }

    @Test
    public void DeletePostAPITest(){
        String postId = "67439c9670f9115bc13cf5da";

        // Step 1: Login and retrieve token
        String token = postApiBO.login();

        // Step 2: Delete post
        PostTemplate deletedPost = postApiBO.deletePost(token, postId);

        // Step 3: Verify post was deleted
        boolean isDeleted = postApiBO.isPostDeleted(deletedPost.get_id());
        Assert.assertTrue(isDeleted);
    }

}
